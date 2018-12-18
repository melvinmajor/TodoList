package todolist.client.cli.parsing;

import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

abstract class LimitedSetParser<T> implements Parser<T> {

    protected abstract String toName(T e);

    protected abstract Collection<T> elements();

    @Override
    public final T parse(String s) throws ParseException {
        if (s == null || s.isBlank()) throw new ParseException();

        var value = get(s);

        if (value == null) throw new ParseException();
        return value;
    }

    private T get(String s) {
        return elements().stream()
                .filter(e -> e.equals(match(s)))
                .findFirst()
                .orElse(null);
    }

    @Override
    public final String onError() {
        var shortByLongName = new HashMap<String, String>();

        for (T element : elements()) {
            var name = toName(element);

            IntStream.range(1, name.length() + 1)
                    .mapToObj(i -> name.substring(0, i))
                    .filter(s1 -> match(s1) != null)
                    .findFirst()
                    .ifPresent(s -> shortByLongName.put(name, s));
        }

        return elements()
                .stream()
                .map(this::toName)
                .map(name -> {
                    var shortName = shortByLongName.get(name);
                    var end = name.substring(shortName.length());
                    var coloredShortName = "@|green " + shortName + "|@";
                    return end.isEmpty() ? coloredShortName : coloredShortName + "[@|cyan " + end + "|@]";
                }).collect(Collectors.joining(" | "));
    }

    private T match(String s) {
        var matches = elements().stream()
                .filter(Objects::nonNull)
                .filter(e -> {
                    var name = toName(e).toLowerCase();
                    return !s.isBlank() && s.length() <= name.length() && name.startsWith(s);
                })
                .limit(2)
                .collect(Collectors.toList());

        return matches.size() != 1 ? null : matches.get(0);
    }
}
