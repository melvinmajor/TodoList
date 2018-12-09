package todolist.client.cli.parsing;

import todolist.client.cli.actions.Action;
import todolist.common.Importance;

import java.time.LocalDate;

public class Parsers {
    public static final Parser<LocalDate> dateParser = new DateParser();
    public static final Parser<Importance> importanceParser = new ImportanceParser();
    public static final Parser<String> stringParser = new StringParser();
    public static final Parser<Action> actionParser = new ActionParser();
}
