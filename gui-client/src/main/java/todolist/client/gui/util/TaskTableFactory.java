package todolist.client.gui.util;

import todolist.client.gui.GuiClient;
import todolist.common.Task;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Factory class to create a {@code JTable} filled with a list of {@code Task} from the {@code GuiClient}
 */
public class TaskTableFactory {

    public static JTable createTable() {
        var header = new String[]{"Description", "Importance", "Completed", "Due"};
        var tasks = new ArrayList<>(GuiClient.instance.getTasks());

        // TODO delegate to BaseClient
        var a = Comparator.<Task, LocalDate>comparing(t -> t.dueDate, Comparator.nullsLast(Comparator.naturalOrder()))
                .thenComparing(t -> t.importance, Comparator.nullsLast(Comparator.naturalOrder()));
        tasks.sort(a);

        var data = generateData(tasks);

        var table = new JTable(data, header);

        var model = new DefaultTableModel(data, header) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table.setModel(model);

        return table;
    }

    private static String[][] generateData(List<Task> tasks) {
        var data = new String[tasks.size()][4];

        for (int i = 0; i < tasks.size(); i++) {
            data[i] = generateLine(tasks.get(i));
        }

        return data;
    }

    private static String[] generateLine(Task task) {
        return new String[]{
                task.description,
                DisplayName.of(task.importance),
                DisplayName.of(task.dueDate),
                DisplayName.of(task.completed)
        };
    }

}
