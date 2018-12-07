package todolist.client.gui;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.*;

import todolist.client.base.BaseClient;
import todolist.common.Task;

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
        List<Image> icons = new ArrayList<Image>();
        icons.add(new ImageIcon("TodoList-icon-16.png").getImage());
        icons.add(new ImageIcon("TodoList-icon-32.png").getImage());
        icons.add(new ImageIcon("TodoList-icon.png").getImage());
        frame.setIconImages(icons);
        frame.setBounds(100, 100, 561, 396);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);
        frame.getContentPane().setBackground(new Color(238, 238, 238));
        
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBorderPainted(false);
        menuBar.setBackground(new Color(238, 238, 238));
        menuBar.setBounds(10, 6, 538, 30);
        frame.getContentPane().add(menuBar);
                        
                JButton btnAddTask = new JButton("Add Task");
                menuBar.add(btnAddTask);
                
                JButton btnEditTask = new JButton("Edit Task");
                menuBar.add(btnEditTask);
                        
                JButton btnDeleteTask = new JButton("Delete Task");
                menuBar.add(btnDeleteTask);

        scrollPane.setBounds(10, 49, 540, 271);
        frame.getContentPane().add(scrollPane);

        JButton btnExit = new JButton("Exit");
        btnExit.addActionListener(e -> onExit());
        btnExit.setBounds(461, 332, 89, 23);
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
