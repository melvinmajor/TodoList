package todolist.client.cli.util;

import todolist.common.Importance;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DisplayString {
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");

    public static String of(LocalDate date) {
        if (date == null) return "";

        var now = LocalDate.now();

        var output = "";
        output += date.format(dateFormatter);
        var diff = ChronoUnit.DAYS.between(now, date);
        output += " (";

        if (diff == 0) output += "@|yellow today|@";
        else if (diff == 1) output += "@|yellow tomorrow|@";
        else if (diff > 0) output += "@|green in " + diff + " days|@";
        else if (diff == -1) output += "@| red yesterday|@";
        else output += "@|red " + -diff + " days ago|@";

        output += ")";

        return output;
    }

    public static String of(Importance importance) {
        if (importance == null) return "";

        var name = importance.name().substring(0, 1) + importance.name().substring(1).toLowerCase();

        switch (importance) {
            case HIGH:
                return "@|red " + name + "|@";
            case MEDIUM:
                return "@|yellow " + name + "|@";
            case LOW:
                return "@|green " + name + "|@";
            default:
                return "";
        }
    }

    public static String of(boolean bool) {
        return bool ? "@|green V|@" : "@|red X|@";
    }

}
