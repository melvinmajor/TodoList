package todolist.client.gui.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DisplayNameTest {

    @Test
    void dateNameTest() {
        Locale.setDefault(Locale.US);
        assertEquals("15 December 2000", DisplayName.of(LocalDate.of(2000, Month.DECEMBER, 15)));
    }

    @Test
    void enumNameTest() {
        assertEquals("December", DisplayName.of(Month.DECEMBER));
    }

    @Test
    void booleanTrueNameTest() {
        assertEquals("V", DisplayName.of(true));
    }

    @Test
    void booleanFalseNameTest() {
        assertEquals("X", DisplayName.of(false));
    }
}