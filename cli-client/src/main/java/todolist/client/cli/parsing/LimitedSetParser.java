package todolist.client.cli.parsing;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

abstract class LimitedSetParser<T> implements Parser<T> {

    protected abstract String toName(T e);

    protected abstract Collection<T> elements();

    @Override
    public final T parse(String s) throws ParseException {
        if (s == null || s.isBlank()) throw new ParseException();

        var v = match(s);
        if (v == null) throw new ParseException();
        return v;
    }

    @Override
    public final String onError() {
        String possibilities = elements()
                .stream()
                .map(this::toName)
                .collect(Collectors.joining(" | "));

        return "Enter " + possibilities;
    }

    private T match(String s) {
        List<T> matches = elements().stream()
                .filter(Objects::nonNull)
                .filter(e -> toName(e).toLowerCase().startsWith(s.toLowerCase()))
                .limit(2)
                .collect(Collectors.toList());

        if (matches.size() != 1) return null;

        return matches.get(0);
    }
}
