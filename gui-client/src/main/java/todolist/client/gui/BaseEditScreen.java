package todolist.client.gui;

import todolist.common.Task;

import javax.swing.*;
import java.util.function.Consumer;

public class BaseEditScreen extends JFrame {
    private final String type;
    private final Consumer<Task> onSelectAction;
    private final JButton actionButton;


    public BaseEditScreen(String type, Consumer<Task> onSelectAction) {
        this.type = type;
        this.onSelectAction = onSelectAction;
        this.actionButton = new JButton(type);

        this.actionButton.addActionListener(e -> {
            // TODO
            Task task = null;

            if (task != null) {
                onSelectAction.accept(task);
            }

        });

        setTitle("TodoList - " + type + " Task");
    }


}
