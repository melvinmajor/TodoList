package todolist.client.cli.parsing;

public interface Parser<T> {
    T parse(String s) throws ParseException;

    String onError();
}
