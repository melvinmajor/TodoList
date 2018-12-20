package argparsing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class InternalArgParser {
    private final Map<String, Option> optionsByName = new HashMap<>();
    private final List<ArgSupplier> argsSupplier;

    InternalArgParser(List<ArgSupplier> argsSupplier, Option[] options) {
        this.argsSupplier = argsSupplier;

        for (var arg : options) {
            insertIfAbsentOrFail(arg.longName, arg, false);
            insertIfAbsentOrFail(arg.shortName, arg, true);
        }
    }


    private void insertIfAbsentOrFail(String name, Option option, boolean shortName) {
        if (name == null) return;
        name = shortName ? "-" + name : "--" + name;
        if (optionsByName.containsKey(name)) throw new IllegalArgumentException("Duplicate argument name");
        optionsByName.put(name, option);
    }

    void parse() {
        for (var supplier : argsSupplier) {
            var args = supplier.get();
            parse(args);
        }
    }

    private void parse(String[] args) {
        var argList = splitOnEquals(args);

        for (int i = 0; i < argList.size(); i++) {
            var arg = argList.get(i);
            var opt = optionsByName.get(arg);
            if (opt == null) continue;
            if (opt.isFlag) {
                opt.setPresent();
                continue;
            }
            if (++i >= argList.size()) continue;
            var value = argList.get(i);
            opt.setValue(value);
            opt.setPresent();
        }

    }

    /**
     * Splits args values if the "=" (equals) sign is present
     *
     * @param args
     * @return a list of values, splitted on equals
     */
    private List<String> splitOnEquals(String[] args) {
        var argList = new ArrayList<String>();
        for (var arg : args) {
            if (arg.contains("=")) {
                var spl = arg.split("=", 2);
                argList.add(spl[0]);
                argList.add(spl[1]);
            } else {
                argList.add(arg);
            }
        }
        return argList;
    }

}
