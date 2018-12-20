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

/**
 * This class is displayed on the screen when the used push the Add button.
 * 
 */
public class AddView implements View {
    
    private JCheckBox dateCheckbox;
    private Choice dayChoice;
    private Choice monthChoice;
    private Choice yearChoice;

    private JCheckBox importanceCheckbox;
    private Choice importanceChoice;

    private JTextField descriptionField;

    private JButton addButton;
    
    /**
     * Fill the new content of the frame
     */
    @Override
    public void fill(Container container) {
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        
        // Check Button
        dateCheckbox = new JCheckBox("Due date", false);
        
        // Field to choice a day beetween 1 and 32
        dayChoice = IntStream.range(1, 32)
                .mapToObj(String::valueOf)
                .collect(choiceCollector);
        // Field to choice a month
        monthChoice = Arrays.stream(Month.values())
                .map(DisplayName::of)
                .collect(choiceCollector);
        
        // Field to choice a year beetween now and 50 years later
        yearChoice = IntStream.iterate(LocalDate.now().getYear(), i -> i + 1)
                .limit(50)
                .mapToObj(String::valueOf)
                .collect(choiceCollector);
        
        container.add(lineOf(dateCheckbox, dayChoice, monthChoice, yearChoice));

        // Check Button
        importanceCheckbox = new JCheckBox("Importance", false);

        // Field to choice a day importance 
        importanceChoice = Arrays.stream(Importance.values())
                .map(DisplayName::of)
                .collect(choiceCollector);

        container.add(lineOf(importanceCheckbox, importanceChoice));

        // Field to enter a description
        descriptionField = newTextField(50, s -> addButton.setEnabled(!s.isBlank()));
        container.add(descriptionField);

        // Button to commit the task
        addButton = newButton("Add Task", this::onAddButtonClick);
        addButton.setEnabled(false);

        // Button to cancel
        var cancelButton = newButton("Cancel", this::goToDefaultView);

        container.add(lineOf(addButton, cancelButton));
    }
    /**
     * Action when the user click on the add button
     */
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
    
    /**
     * Action to return to the base menu
     */
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
