package todolist.client.cli.parsing;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

class DateParser implements Parser<LocalDate> {
    @Override
    public LocalDate parse(String s) throws ParseException {
        var formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        if (s.split("/").length == 2) s = LocalDate.now().getYear() + "/" + s;

        try {
            return LocalDate.parse(s, formatter);
        } catch (DateTimeParseException e) {
            throw new ParseException();
        }
    }

    @Override
    public String onError() {
        return "Invalid date format (ex: [2018/]12/31)";
    }

    @Override
    public Type type() {
        return Type.DATE;
    }
}
