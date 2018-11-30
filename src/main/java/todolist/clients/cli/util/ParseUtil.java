package todolist.clients.cli.util;

import todolist.common.Importance;
import todolist.common.TaskBuilder;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ParseUtil {

    private static final List<CategoryParser> pairs = List.of(
            new CategoryParser("importance", (v, b) -> {
                Importance match = match(List.of(Importance.values()), Importance::name, v);
                b.setImportance(match);
            }),
            new CategoryParser("due", (v, b) -> {
                var date = parseDate(v);
                b.setDueDate(date);
            }),
            new CategoryParser("completed", (v, b) -> {
                boolean completed = match(List.of(true, false), String::valueOf, v);
                b.setCompleted(completed);
            })
    );

    public static TaskBuilder parseTask(List<String> args) {
        var builder = new TaskBuilder();
        var used = new ArrayList<String>();

        for (String a : args) {
            if (!a.contains(":")) continue;

            String[] split = a.split(":");
            if (split.length != 2) {
                System.err.println("Invalid argument: " + a + " (ignored)");
                continue;
            }

            var category = split[0].toLowerCase();
            var value = split[1].toLowerCase();

            for (CategoryParser categoryParser : pairs) {
                // TODO avoid collisions
                if (categoryParser.name.toLowerCase().startsWith(category)) {
                    categoryParser.parse.accept(value, builder);
                    used.add(a);
                    break;
                }
            }

        }

        args.removeAll(used);

        String description = String.join(" ", args);
        builder.setDescription(description);

        return builder;
    }

    // TODO ommit year
    private static LocalDate parseDate(String s) {
        try {
            return LocalDate.parse(s);
        } catch (DateTimeParseException e) {
            System.err.println("Invalid date format (ex: 2007-12-03)");
            return null;
        }
    }

    private static class CategoryParser {
        final BiConsumer<String, TaskBuilder> parse;
        final String name;

        private CategoryParser(String name, BiConsumer<String, TaskBuilder> parse) {
            this.parse = parse;
            this.name = name;
        }
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

    public static int getIntOrElse(String s, int or) {
        try {
            return  Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return or;
        }
    }

}
