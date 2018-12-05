package todolist.client.cli.parsing;

import org.junit.jupiter.api.Test;
import todolist.common.Importance;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ImportanceParserTest {

    @Test
    void testFullName() {
        var i = Parsers.parse("high", Type.IMPORTANCE);
        assertEquals(Importance.HIGH, i);
    }

    @Test
    void testShortName() {
        var i = Parsers.parse("m", Type.IMPORTANCE);
        assertEquals(Importance.MEDIUM, i);
    }


}