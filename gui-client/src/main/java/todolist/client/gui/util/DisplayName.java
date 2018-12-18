package todolist.client.gui.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * A collection of function to get a display string of some objects
 */
public class DisplayName {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");

    public static String of(LocalDate date) {
        return date == null ? "" : date.format(formatter);
    }

    public static String of(Enum e) {
        if (e == null) return "";
        else return e.name().length() == 1
                ? e.name()
                : e.name().substring(0, 1).toUpperCase() + e.name().substring(1).toLowerCase();
    }

    public static String of(boolean b) {
        return b ? "V" : "X";
    }

}
