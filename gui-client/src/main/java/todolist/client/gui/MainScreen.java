package todolist.client.gui;

import todolist.client.base.BaseClient;
import todolist.common.Task;

import javax.swing.*;
import java.util.Collection;

public class MainScreen extends BaseClient {

    private JFrame frame;
    private JScrollPane scrollPane = new JScrollPane();

    @Override
    public void run() {
        super.run();
        initialize();
        this.frame.setVisible(true);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 561, 396);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        scrollPane.setBounds(10, 102, 483, 167);
        frame.getContentPane().add(scrollPane);

        JButton btnAddTask = new JButton("Add Task");
        btnAddTask.setBounds(10, 11, 89, 23);
        frame.getContentPane().add(btnAddTask);

        JButton btnEditTask = new JButton("Edit Task");
        btnEditTask.setBounds(210, 11, 89, 23);
        frame.getContentPane().add(btnEditTask);

        JButton btnDeleteTask = new JButton("Delete Task");
        btnDeleteTask.setBounds(377, 11, 116, 23);
        frame.getContentPane().add(btnDeleteTask);

        JButton btnExit = new JButton("Exit");
        btnExit.addActionListener(e -> onExit());
        btnExit.setBounds(418, 304, 89, 23);
        frame.getContentPane().add(btnExit);
    }

    private JTable createTable() {
        String[] header = new String[]{"Description", "Importance", "Due", "completed"};
        String[][] data = new String[tasks.size()][4];

        for (int i = 0; i < tasks.size(); i++) {
            var task = tasks.get(i);
            String[] temp = new String[]{
                    task.description,
                    task.description,
                    task.description,
                    task.description
            };
            data[i] = temp;
        }

        return new JTable(data, header);
    }

    private void setTable() {
        scrollPane.getViewport().removeAll();
        var table = createTable();
        scrollPane.getViewport().add(table);
    }

    @Override
    public void onConnectionError() {
        JOptionPane.showMessageDialog(null, "Error connection with the server failed", "ERROR", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void onExit() {
        System.exit(0);
    }

    @Override
    public boolean onUpdate(Collection<Task> tasks) {
        var temp = super.onUpdate(tasks);
        setTable();
        return temp;
    }
}
