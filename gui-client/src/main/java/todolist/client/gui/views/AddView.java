package todolist.client.gui.views;

import todolist.client.gui.GuiClient;
import todolist.client.gui.util.DisplayName;
import todolist.client.gui.util.SwingUtils;
import todolist.common.Command;
import todolist.common.Importance;
import todolist.common.Packet;
import todolist.common.TaskBuilder;

import javax.swing.*;
import java.awt.*;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.stream.IntStream;

import static todolist.client.gui.util.SwingUtils.*;

public class AddView implements View {

    private JCheckBox dateCheckbox;
    private Choice dayChoice;
    private Choice monthChoice;
    private Choice yearChoice;

    private JCheckBox importanceCheckbox;
    private Choice importanceChoice;

    private JTextField descriptionField;

    private JButton addButton;

    @Override
    public void fill(Container container) {
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        dateCheckbox = new JCheckBox("Due date", false);

        dayChoice = IntStream.range(1, 32)
                .mapToObj(String::valueOf)
                .collect(choiceCollector);

        monthChoice = Arrays.stream(Month.values())
                .map(DisplayName::of)
                .collect(choiceCollector);

        yearChoice = IntStream.iterate(LocalDate.now().getYear(), i -> i + 1)
                .limit(50)
                .mapToObj(String::valueOf)
                .collect(choiceCollector);

        container.add(lineOf(dateCheckbox, dayChoice, monthChoice, yearChoice));


        importanceCheckbox = new JCheckBox("Importance", false);

        importanceChoice = Arrays.stream(Importance.values())
                .map(DisplayName::of)
                .collect(choiceCollector);

        container.add(lineOf(importanceCheckbox, importanceChoice));


        descriptionField = newTextField(50, s -> addButton.setEnabled(!s.isBlank()));
        container.add(descriptionField);


        addButton = newButton("Add Task", this::onAddButtonClick);
        addButton.setEnabled(false);

        var cancelButton = newButton("Cancel", this::goToDefaultView);

        container.add(lineOf(addButton, cancelButton));
    }

    private void onAddButtonClick() {
        var description = descriptionField.getText();


        var dueDate = dateCheckbox.isSelected() ? getDate() : null;
        if (dateCheckbox.isSelected() && dueDate == null) {
            SwingUtils.errorPopup("Invalid date");
            return;
        }


        var importance = importanceCheckbox.isSelected()
                ? Importance.valueOf(importanceChoice.getSelectedItem().toUpperCase())
                : null;

        var task = new TaskBuilder()
                .setId(GuiClient.instance.nextAvailableId())
                .setDescription(description)
                .setDueDate(dueDate)
                .setImportance(importance)
                .build();

        GuiClient.instance.sendPacket(new Packet(Command.ADD, task));

        goToDefaultView();
    }

    private void goToDefaultView() {
        var mainScreen = GuiClient.instance.screen;
        mainScreen.swapView(mainScreen.defaultView);
    }


    private LocalDate getDate() {
        var year = Integer.parseInt(yearChoice.getSelectedItem());
        var month = Month.valueOf(monthChoice.getSelectedItem().toUpperCase());
        var day = Integer.parseInt(dayChoice.getSelectedItem());

        try {
            return LocalDate.of(year, month, day);
        } catch (DateTimeException ex) {
            return null;
        }
    }


}
