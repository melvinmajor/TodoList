package todolist.client.gui.views;

import todolist.client.gui.GuiClient;
import todolist.client.gui.util.TaskTableFactory;

import javax.swing.*;
import java.awt.*;

import static todolist.client.gui.util.SwingUtils.newButton;

public class MainView implements View {

    private final JScrollPane scrollPane = new JScrollPane();

    public void updateTaskTable() {
        scrollPane.getViewport().removeAll();

        // TODO keep sort index
        var table = TaskTableFactory.createTable();

        scrollPane.setViewportView(table);
    }

    @Override
    public void fill(Container container) {
        
        var mainLayout = new GridBagLayout();
        mainLayout.columnWidths = new int[]{540, 0};
        mainLayout.rowHeights = new int[]{30, 271, 23, 0};
        mainLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        mainLayout.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
        container.setLayout(mainLayout);

        addMenuBar(container);
        addScrollPaneLayout(container);
        addExitButton(container);
    }

    private void addMenuBar(Container container) {
        var menuBar = new JMenuBar();
        menuBar.setBorderPainted(false);
        menuBar.setBackground(new Color(238, 238, 238));

        var addTaskButton = newButton("Add Task", () -> {
            var mainScreen = GuiClient.instance.screen;
            mainScreen.swapView(mainScreen.addView);
        });

        menuBar.add(addTaskButton);


        var completeTaskButton = newButton("Complete Task", () -> {
            var mainScreen = GuiClient.instance.screen;
            mainScreen.swapView(mainScreen.completeView);
        });
        menuBar.add(completeTaskButton);

        var deleteTaskButton = newButton("Delete Task", () -> {
            var mainScreen = GuiClient.instance.screen;
            mainScreen.swapView(mainScreen.deleteView);
        });
        menuBar.add(deleteTaskButton);

        GridBagConstraints gbc_menuBar = new GridBagConstraints();
        gbc_menuBar.anchor = GridBagConstraints.NORTH;
        gbc_menuBar.fill = GridBagConstraints.HORIZONTAL;
        gbc_menuBar.insets = new Insets(0, 0, 5, 0);
        gbc_menuBar.gridx = 0;
        gbc_menuBar.gridy = 0;
        container.add(menuBar, gbc_menuBar);
    }

    private void addScrollPaneLayout(Container container) {
        GridBagConstraints scrollPaneConstraint = new GridBagConstraints();
        scrollPaneConstraint.fill = GridBagConstraints.BOTH;
        scrollPaneConstraint.insets = new Insets(0, 0, 5, 0);
        scrollPaneConstraint.gridx = 0;
        scrollPaneConstraint.gridy = 1;
        container.add(scrollPane, scrollPaneConstraint);
    }

    private void addExitButton(Container container) {
        var exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> GuiClient.instance.onExit());

        GridBagConstraints exitButtonConstraint = new GridBagConstraints();
        exitButtonConstraint.anchor = GridBagConstraints.EAST;
        exitButtonConstraint.fill = GridBagConstraints.VERTICAL;
        exitButtonConstraint.gridx = 0;
        exitButtonConstraint.gridy = 2;

        container.add(exitButton, exitButtonConstraint);
    }


}
