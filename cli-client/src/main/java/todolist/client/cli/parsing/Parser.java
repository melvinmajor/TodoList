package todolist.client.cli.parsing;

interface Parser<T> {
    T parse(String s) throws ParseException;

    String onError();

    Type type();
}
