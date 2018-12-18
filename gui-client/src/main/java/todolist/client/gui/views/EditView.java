package todolist.client.gui.views;

import todolist.client.gui.GuiClient;
import todolist.client.gui.Screen;
import todolist.client.gui.util.SwingUtils;
import todolist.common.Task;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.function.Consumer;

import static todolist.client.gui.util.SwingUtils.lineOf;
import static todolist.client.gui.util.SwingUtils.newButton;

public abstract class EditView implements View {
    private final Consumer<Task> onActionListener;
    private final String actionName;
    private Task selectedTask;

    public EditView(String actionName, Consumer<Task> onActionListener) {
        this.actionName = actionName;
        this.onActionListener = onActionListener;
    }

    @Override
    public void fill(Container container) {
        container.setLayout(new BorderLayout());

        var tasks = GuiClient.instance.getTasks();

        var radioButtons = new ArrayList<JRadioButton>();


        var panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        for (var task : tasks) {
            var radioButton = new JRadioButton();
            radioButtons.add(radioButton);
            radioButton.addItemListener(e -> {
                var selected = e.getStateChange() == ItemEvent.SELECTED;
                if (selected) this.selectedTask = task;
            });
            var label = new JLabel(task.description);
            panel.add(SwingUtils.lineOf(radioButton, label));
        }

        var group = new ButtonGroup();
        radioButtons.forEach(group::add);

        container.add(panel, BorderLayout.CENTER);

        var cancelButton = newButton("Cancel", () -> {
            Screen screen = GuiClient.instance.screen;
            screen.swapView(screen.defaultView);
        });

        var actionButton = newButton(actionName, () -> {
            if (selectedTask == null) {
                SwingUtils.errorPopup("No task selected");
            } else {
                onActionListener.accept(selectedTask);
                Screen screen = GuiClient.instance.screen;
                screen.swapView(screen.defaultView);
            }
        });

        container.add(lineOf(cancelButton, actionButton), BorderLayout.SOUTH);

    }

}
