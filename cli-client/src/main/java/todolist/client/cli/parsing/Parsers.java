package todolist.client.cli.parsing;

import todolist.client.cli.actions.Action;
import todolist.common.Importance;

import java.time.Month;

public class Parsers {
    public static final Parser<Importance> importanceParser = new EnumParser<>(Importance.class);

    public static final Parser<Integer> dayParser = new IntParser(1, 31);
    public static final Parser<Month> monthParser = new EnumParser<>(Month.class);
    public static final Parser<Integer> yearParser = new IntParser();

    public static final Parser<String> stringParser = new StringParser();

    public static final Parser<Action> actionParser = new ActionParser();
}
