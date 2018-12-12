package todolist.client.cli.parsing;

import java.util.function.Predicate;

public class IntParser implements Parser<Integer> {
    private final Predicate<Integer> boundChecker;
    private boolean nan;

    public IntParser(int min, int max) {
        this.boundChecker = e -> e >= min && e <= max;
    }

    public IntParser() {
        this.boundChecker = e -> true;
    }

    @Override
    public Integer parse(String s) throws ParseException {
        nan = false;
        int value;
        try {
            value = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            nan = true;
            throw new ParseException();
        }

        if (!boundChecker.test(value)) throw new ParseException();

        return value;
    }

    @Override
    public String onError() {
        return nan ? "Not a number" : "Out of bounds";
    }
}
