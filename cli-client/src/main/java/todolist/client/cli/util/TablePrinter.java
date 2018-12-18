package todolist.client.cli.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.fusesource.jansi.Ansi.ansi;

/**
 * Provides the ability to create a table representation of tasks
 */
public class TablePrinter {
    private final List<String> header;
    private final List<List<String>> content;

    public TablePrinter(List<String> header, List<List<String>> content) {
        this.header = header;
        this.content = content;

        if (!validate()) throw new IllegalArgumentException();
    }

    public void print() {
        int[] colLength = new int[header.size()];
        for (int i = 0; i < header.size(); i++) {
            colLength[i] = getColumnLength(i);
        }

        var lines = new ArrayList<String>();
        lines.add("");

        var coloredHeader = header.stream()
                .map(s -> "@|blue " + s + "|@")
                .collect(Collectors.toList());
        lines.add(makeLine(coloredHeader, colLength));

        lines.add(makeUnderline(colLength));

        content.forEach(l -> lines.add(makeLine(l, colLength)));

        lines.add("");

        for (String l : lines) System.out.println(ansi().render(l));
    }

    private String makeUnderline(int[] colLength) {
        var builder = new StringBuilder();
        for (int i = 0; i < colLength.length; i++) {
            var c = colLength[i];
            if (c == 0) continue;
            int padding = i == 0 ? 1 : 2;
            for (int j = 0; j < padding + c; j++) builder.append("─");
            if (i != colLength.length - 1) builder.append("┼");
        }
        return builder.toString();
    }

    private String makeLine(List<String> strings, int[] colLength) {
        var line = new StringBuilder();
        var separator = "│";
        for (int i = 0; i < strings.size(); i++) {
            if (colLength[i] == 0) continue;

            if (line.length() != 0) {
                line.append(separator);
                line.append(" ");
            }

            var str = strings.get(i);
            line.append(str);
            line.append(" ");

            int diff = colLength[i] - escapedStringLength(str);
            for (int j = 0; j < diff; j++) line.append(" ");
        }
        return line.toString();
    }

    private int getColumnLength(int index) {
        int length = header.get(index).length();

        var itemsAtIndex = content.stream()
                .map(e -> e.get(index))
                .collect(Collectors.toList());

        int maxLength = maxLength(itemsAtIndex);

        return maxLength == 0 ? 0 : Math.max(length, maxLength);
    }

    private int maxLength(List<String> strings) {
        return strings.stream()
                .mapToInt(this::escapedStringLength)
                .max()
                .orElse(0);
    }

    private boolean validate() {
        var headerSize = header.size();
        return content.stream()
                .mapToInt(List::size)
                .filter(l -> l != headerSize)
                .limit(1)
                .findAny()
                .isEmpty();
    }

    private final Pattern colorStartPattern = Pattern.compile("@\\|\\w+ ");
    private final Pattern colorEndPattern = Pattern.compile("\\|@");

    private int escapedStringLength(String input) {
        var escapedString = colorStartPattern.matcher(input).replaceAll("");
        escapedString = colorEndPattern.matcher(escapedString).replaceAll("");
        return escapedString.length();
    }

}
