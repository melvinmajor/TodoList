package argparsing;

import java.util.ArrayList;
import java.util.List;

public class ArgParser {
    private List<ArgSupplier> argsSupplier = new ArrayList<>();
    private Option[] options;

    public ArgParser(Option... options) {
        this.options = options;
    }

    public ArgParser addSource(String[] args) {
        argsSupplier.add(() -> args);
        return this;
    }

    public ArgParser addFileSource(String path) {
        argsSupplier.add(new FileArgSupplier(path));
        return this;
    }

    public void parse() {
        if (argsSupplier.isEmpty()) throw new IllegalStateException("Need at least one arg supplier");
        new InternalArgParser(argsSupplier, options).parse();
    }

}
