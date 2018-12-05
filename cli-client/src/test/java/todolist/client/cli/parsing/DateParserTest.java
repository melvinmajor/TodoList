package todolist.client.cli.parsing;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DateParserTest {
    private LocalDate expected = LocalDate.of(2018, Month.DECEMBER, 1);

    @Test
    void shortFormatTest() {
        var s = "2018/12/01";
        LocalDate date = Parsers.parse(s, Type.DATE);
        assertEquals(expected, date);
    }

    @Test
    void longFormatTest() {
        var s = "12/01";
        LocalDate date = Parsers.parse(s, Type.DATE);
        assertEquals(expected, date);
    }

    @Test
    void wrongFormatTest() {
        assertNull(Parsers.parse("05 january", Type.DATE));
    }

}