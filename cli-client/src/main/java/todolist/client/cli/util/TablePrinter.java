package todolist.client.cli.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

        lines.add(makeLine(header, colLength, true));

        List<String> underline = Arrays.stream(colLength).mapToObj(e -> {
            if (e == 0) return "";
            var builder = new StringBuilder();
            for (int i = 0; i < e + 2; i++) builder.append("─");
            return builder.toString();
        }).collect(Collectors.toList());

        lines.add(makeLine(underline, colLength, false));

        content.forEach(l -> lines.add(makeLine(l, colLength, true)));

        lines.add("");

        lines.forEach(System.out::println);
    }

    private String makeLine(List<String> strings, int[] colLength, boolean spaces) {
        var line = new StringBuilder();
        var separator = "│";
        for (int i = 0; i < strings.size(); i++) {
            if (colLength[i] == 0) continue;

            if (spaces && line.length() != 0) line.append(" ");
            line.append(separator);
            if (spaces) line.append(" ");

            var str = strings.get(i);
            line.append(str);

            int diff = colLength[i] - str.length();
            for (int j = 0; j < diff; j++) line.append(" ");
        }
        if (spaces) line.append(" ");
        line.append(separator);
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
                .mapToInt(String::length)
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

}
