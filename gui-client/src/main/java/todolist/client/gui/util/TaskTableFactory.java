package todolist.client.gui.util;

import todolist.client.gui.GuiClient;
import todolist.common.Task;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Factory class to create a {@code JTable} filled with a list of {@code Task} from the {@code GuiClient}
 */
public class TaskTableFactory {

    public static JTable createTable() {
        var header = new String[]{"Description", "Importance", "Due", "Completed" };
        var tasks = new ArrayList<>(GuiClient.instance.getTasks());

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
