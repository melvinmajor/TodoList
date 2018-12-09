package todolist.client.cli.parsing;

public class StringParser implements Parser<String> {
    @Override
    public String parse(String s) throws ParseException {
        if (s.isBlank()) throw new ParseException();
        return s;
    }

    @Override
    public String onError() {
        return "input can't be empty";
    }

}
