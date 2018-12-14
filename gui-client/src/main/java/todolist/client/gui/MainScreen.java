package todolist.client.gui;

import todolist.client.base.BaseClient;
import todolist.common.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MainScreen extends BaseClient {

    private JFrame frame;
    private JScrollPane scrollPane = new JScrollPane();

    private SortType sortType = SortType.DUE_DATE;

    /**
     * Launch the main frame of the GUI client.
     */
    @Override
    public void run() {
        super.run();
        initialize();
        frame.setMinimumSize(new Dimension(561, 396));
        this.frame.setVisible(true);
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setTitle("TodoList");

        var icons = Stream.of("TodoList-icon-16.png", "TodoList-icon-32.png", "TodoList-icon.png")
                .map(e -> Toolkit.getDefaultToolkit().getImage(e)).collect(Collectors.toList());

        frame.setIconImages(icons);
        frame.setBounds(100, 100, 561, 396);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(238, 238, 238));
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{540, 0};
        gridBagLayout.rowHeights = new int[]{30, 271, 23, 0};
        gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
        frame.getContentPane().setLayout(gridBagLayout);

        var menuBar = new JMenuBar();
        menuBar.setBorderPainted(false);
        menuBar.setBackground(new Color(238, 238, 238));
        GridBagConstraints gbc_menuBar = new GridBagConstraints();
        gbc_menuBar.anchor = GridBagConstraints.NORTH;
        gbc_menuBar.fill = GridBagConstraints.HORIZONTAL;
        gbc_menuBar.insets = new Insets(0, 0, 5, 0);
        gbc_menuBar.gridx = 0;
        gbc_menuBar.gridy = 0;
        frame.getContentPane().add(menuBar, gbc_menuBar);

        var addTaskButton = new JButton("Add Task");
        menuBar.add(addTaskButton);
        addTaskButton.addActionListener(e -> {
            new AddTaskScreen(this).run();
        });

        var completeTaskButton = new JButton("Complete Task");
        menuBar.add(completeTaskButton);
        completeTaskButton.addActionListener(e -> new BaseEditScreen("Complete", this::completeTask));

        var deleteTaskButton = new JButton("Delete Task");
        menuBar.add(deleteTaskButton);
        deleteTaskButton.addActionListener(e -> new BaseEditScreen("Remove", this::removeTask));

        GridBagConstraints task_Panel = new GridBagConstraints();
        task_Panel.fill = GridBagConstraints.BOTH;
        task_Panel.insets = new Insets(0, 0, 5, 0);
        task_Panel.gridx = 0;
        task_Panel.gridy = 1;
        frame.getContentPane().add(scrollPane, task_Panel);

        var exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> onExit());
        GridBagConstraints gbc_exitButton = new GridBagConstraints();
        gbc_exitButton.anchor = GridBagConstraints.EAST;
        gbc_exitButton.fill = GridBagConstraints.VERTICAL;
        gbc_exitButton.gridx = 0;
        gbc_exitButton.gridy = 2;
        frame.getContentPane().add(exitButton, gbc_exitButton);
    }

    private String importanceName(Importance i) {
        return i != null ? i.name().substring(0, 1).toUpperCase() + i.name().substring(1).toLowerCase() : "";
    }

    private JTable createTable() {
        var headerItemByType = new LinkedHashMap<SortType, String>();
        headerItemByType.put(SortType.DESCRIPTION, "Description");
        headerItemByType.put(SortType.IMPORTANCE, "Importance");
        headerItemByType.put(SortType.COMPLETED, "Completed");
        headerItemByType.put(SortType.DUE_DATE, "Due");

        var header = headerItemByType
                .keySet()
                .stream()
                .map(e -> e == this.sortType ? headerItemByType.get(e) + " ^" : headerItemByType.get(e))
                .toArray(String[]::new);

        String[][] data = new String[tasks.size()][4];
        DateTimeFormatter jeanLuc = DateTimeFormatter.ofPattern("dd LLLL yyyy");

        // TODO sort
        switch (sortType) {
            case DESCRIPTION:
                break;
            case IMPORTANCE:
                break;
            case DUE_DATE:
                break;
            case COMPLETED:
                break;
        }

        for (int i = 0; i < tasks.size(); i++) {
            var task = tasks.get(i);
            var temp = new String[]{
                    task.description,
                    importanceName(task.importance),
                    task.dueDate != null ? task.dueDate.format(jeanLuc) : "",
                    // V for false, X for true.
                    task.completed ? "V" : "X"
            };

            data[i] = temp;
        }
        var model = new DefaultTableModel(data, header) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        var table = new JTable(data, header);
        table.setModel(model);

        table.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                var index = table.columnAtPoint(e.getPoint());
                if (index == 0) sortType = SortType.DESCRIPTION;
                else if (index == 1) sortType = SortType.IMPORTANCE;
                else if (index == 2) sortType = SortType.COMPLETED;
                else if (index == 3) sortType = SortType.DUE_DATE;

                setTable();
            }
        });

        return table;
    }

    private void setTable() {
        scrollPane.getViewport().removeAll();
        var table = createTable();
        scrollPane.getViewport().add(table.getTableHeader());
        scrollPane.setViewportView(table);
    }

    /**
     * If a connection error occurs while trying to connect to the server, an error
     * message appears.
     */
    @Override
    public void onConnectionError() {
        JOptionPane.showMessageDialog(null, "Error connection with the server failed", "ERROR",
                JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void onExit() {
        System.exit(0);
    }

    /**
     * Update content from the server in the table initialized in the content of the
     * frame.
     */
    @Override
    public boolean onUpdate(Collection<Task> tasks) {
        var temp = super.onUpdate(tasks);
        setTable();
        return temp;
    }

    public void addTask(Task task) {
        sendQuery(new Query(Command.ADD, task));
    }

    public void removeTask(Task task) {
        sendQuery(new Query(Command.REMOVE, task));
    }


    public void completeTask(Task task) {
        var completeTask = new TaskBuilder(task).setCompleted(true).build();
        sendQuery(new Query(Command.EDIT, completeTask));
    }

    @Override
    public int nextAvailableId() {
        return super.nextAvailableId();
    }


}
