package todolist.client.cli.parsing;

import org.junit.jupiter.api.Test;
import todolist.common.Importance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ImportanceParserTest {

    @Test
    void testFullName() {
        try {
            var i = Parsers.importanceParser.parse("high");
            assertEquals(Importance.HIGH, i);
        } catch (ParseException e) {
            fail();
        }
    }

    @Test
    void testShortName() {
        try {
            var i = Parsers.importanceParser.parse("m");
            assertEquals(Importance.MEDIUM, i);
        } catch (ParseException e) {
            fail();
        }
    }

}