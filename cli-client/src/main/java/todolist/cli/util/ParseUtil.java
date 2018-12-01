package todolist.cli.util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ParseUtil {

    // TODO ommit year
    public static LocalDate parseDate(String s) {
        try {
            return LocalDate.parse(s);
        } catch (DateTimeParseException e) {
            System.err.println("Invalid date format (ex: 2007-12-03)");
            return null;
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

    public static Integer getInt(String s) {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
