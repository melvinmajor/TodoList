package todolist;

import org.junit.jupiter.api.Test;
import todolist.commands.DoneCommand;
import todolist.commands.HelpCommand;
import todolist.commands.ListCommand;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class ArgParserTest {
    @Test
    void fullNameTest() {
        var command = ArgParser.parse(new String[]{"ls"});
        assertTrue(command instanceof ListCommand);
    }

    @Test
    void shortNameTest() {
        var command = ArgParser.parse(new String[]{"d"});
        assertTrue(command instanceof DoneCommand);
    }

    @Test
    void noArgsTest() {
        var command = ArgParser.parse(new String[]{});
        assertTrue(command instanceof HelpCommand);
    }

    @Test
    void wrongArgTest() {
        var command = ArgParser.parse(new String[]{"notACommand"});
        assertTrue(command instanceof HelpCommand);
    }

}