package todolist.client.cli.parsing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringParserTest {

    @Test
    void testEmptyString() {
        assertThrows(ParseException.class, () -> Parsers.stringParser.parse(""));
    }

    @Test
    void testString() {
        try {
            var str = Parsers.stringParser.parse("abc");
            assertEquals("abc", str);
        } catch (ParseException e) {
            fail();
        }
    }
}