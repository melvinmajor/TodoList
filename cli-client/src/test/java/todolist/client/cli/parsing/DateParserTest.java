package todolist.client.cli.parsing;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;

class DateParserTest {
    private LocalDate expected = LocalDate.of(2018, Month.DECEMBER, 1);

    @Test
    void longFormatTest() {
        var s = "2018/12/01";
        try {
            var date = Parsers.dateParser.parse(s);
            assertEquals(expected, date);
        } catch (ParseException e) {
            fail();
        }
    }

    @Test
    void shortFormatTest() {
        var s = "12/01";
        try {
            var date = Parsers.dateParser.parse(s);
            assertEquals(expected, date);
        } catch (ParseException e) {
            fail();
        }
    }

    @Test
    void wrongFormatTest() {
        assertThrows(ParseException.class, () -> Parsers.dateParser.parse("05 january"));
    }

}