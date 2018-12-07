package todolist.client.gui;

import todolist.client.base.BaseClient;
import todolist.common.Task;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainScreen extends BaseClient {

    private JFrame frame;
    private JScrollPane scrollPane = new JScrollPane();

    /**
     * @wbp.parser.entryPoint
     */
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
        frame.setTitle("TodoList");

        var icons = Stream.of("TodoList-icon-16.png", "TodoList-icon-32.png", "TodoList-icon.png")
                .map(e -> Toolkit.getDefaultToolkit().getImage(e))
                .collect(Collectors.toList());

        frame.setIconImages(icons);
        frame.setBounds(100, 100, 561, 396);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.getContentPane().setBackground(new Color(238, 238, 238));

        var menuBar = new JMenuBar();
        menuBar.setBorderPainted(false);
        menuBar.setBackground(new Color(238, 238, 238));
        menuBar.setBounds(10, 6, 538, 30);
        frame.getContentPane().add(menuBar);

        var addTaskButton = new JButton("Add Task");
        menuBar.add(addTaskButton);

        var editTaskButton = new JButton("Edit Task");
        menuBar.add(editTaskButton);

        var DeleteTaskButton = new JButton("Delete Task");
        menuBar.add(DeleteTaskButton);

        scrollPane.setBounds(10, 49, 540, 271);
        frame.getContentPane().add(scrollPane);

        var exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> onExit());
        exitButton.setBounds(461, 332, 89, 23);
        frame.getContentPane().add(exitButton);
    }

    private JTable createTable() {
        String[] header = new String[]{"Description", "Importance", "Due", "completed"};
        String[][] data = new String[tasks.size()][4];

        for (int i = 0; i < tasks.size(); i++) {
            var task = tasks.get(i);
            String[] temp = new String[]{
                    // TODO
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
        scrollPane.setViewportView(table);
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
