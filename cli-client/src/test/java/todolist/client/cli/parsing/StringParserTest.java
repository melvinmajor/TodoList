package todolist.client.cli.parsing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class StringParserTest {

    @Test
    void testEmptyString() {
        var str = Parsers.parse("", Type.STRING);
        assertNull(str);
    }

    @Test
    void testString() {
        var str = Parsers.parse("abc", Type.STRING);
        assertEquals("abc", str);
    }
}