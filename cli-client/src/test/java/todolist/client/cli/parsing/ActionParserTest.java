package todolist.client.cli.parsing;

import org.junit.jupiter.api.Test;
import todolist.client.cli.actions.Action;
import todolist.client.cli.actions.AddAction;
import todolist.client.cli.actions.ListAction;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class ActionParserTest {
    @Test
    void testAdd() {
        Action action = null;
        try {
            action = Parsers.actionParser.parse("add");
        } catch (ParseException e) {
            fail();
        }
        assertTrue(action instanceof AddAction);
    }

    @Test
    void testList() {
        Action action = null;
        try {
            action = Parsers.actionParser.parse("ls");
        } catch (ParseException e) {
            fail();
        }
        assertTrue(action instanceof ListAction);
    }

}
