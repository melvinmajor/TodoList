package todolist.cli.util;

import todolist.common.Importance;
import todolist.common.TaskBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ArgParser {

    public static TaskBuilder parseTask(List<String> args) {
        //TODO regex

        var builder = new TaskBuilder();
        var used = new ArrayList<String>();
        for (String a : args) {
            if (a.startsWith("i:")) {
                String[] split = a.split(":");
                if (split.length == 2) {
                    builder.setImportance(parseImportance(split[1]));
                }
                used.add(a);
            }
            // TODO
        }
        args.removeAll(used);

        String description = String.join(" ", args);
        builder.setDescription(description);

        return builder;
    }

    private static Importance parseImportance(String s) {
        List<Importance> values = List.of(Importance.values());
        return match(values, Importance::name, s);
    }

    public static <T> T match(Collection<T> elements, Function<T, String> toString, String maybeMatch) {
        if (maybeMatch == null || maybeMatch.isEmpty()) return null;

        BiFunction<String, String, Boolean> startsWith = (a, b) -> a.toLowerCase().startsWith(b.toLowerCase());

        List<T> matches = elements.stream()
                .filter(e -> startsWith.apply(toString.apply(e), maybeMatch))
                .collect(Collectors.toList());

        if (matches.size() != 1) return null;

        return matches.get(0);
    }

}
