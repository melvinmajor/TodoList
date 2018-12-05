package todolist.client.cli.parsing;

import org.junit.jupiter.api.Test;
import todolist.client.cli.actions.Action;
import todolist.client.cli.actions.AddAction;
import todolist.client.cli.actions.ListAction;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ActionParserTest {
    @Test
    void testAdd() {
        Action action = Parsers.parse("add", Type.ACTION);
        assertTrue(action instanceof AddAction);
    }

    @Test
    void testList() {
        Action action = Parsers.parse("ls", Type.ACTION);
        assertTrue(action instanceof ListAction);
    }
}
