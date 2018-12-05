package todolist.client.cli.parsing;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Parsers {
    private static final Map<Type, Parser> parserByName = List.of(
            new DateParser(),
            new ImportanceParser(),
            new TaskByIdParser(),
            new StringParser(),
            new ActionParser())
            .stream()
            .collect(Collectors.toMap(Parser::type, Function.identity()));


    public static <T> T parse(String s, Type type) {
        Parser<T> parser = parserByName.get(type);
        if (parser == null) throw new IllegalArgumentException();

        try {
            return parser.parse(s);
        } catch (ParseException e) {
            System.err.println(parser.onError());
        }
        return null;
    }
}
