package todolist.client.gui;

import todolist.common.Importance;
import todolist.common.TaskBuilder;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.Month;

public class AddTask {
    private final AddTaskScreen addTaskScreen;
    private final MainScreen mainScreen;
    
    /**
     * Initialization method, it links the main screen and the add task screen.
     * @param addTaskScreen The screen when add task frame is requested.
     * @param mainScreen The screen of the main frame of the GUI client.
     */
    public AddTask(AddTaskScreen addTaskScreen, MainScreen mainScreen) {
        this.addTaskScreen = addTaskScreen;
        this.mainScreen = mainScreen;
    }

    /**
     * A method which stores the user content in a new task.
     */
    public void onAddButtonPressed() {
        var builder = new TaskBuilder();

        var description = addTaskScreen.getDescriptionArea().getText();
        if (description.isEmpty()) {
            // TODO popup
            return;
        } else {
            builder.setDescription(description);
        }

        if (!addTaskScreen.getImportanceChoice().getSelectedItem().equals("none")) {
            var importance = Importance.valueOf(addTaskScreen.getImportanceChoice().getSelectedItem().toUpperCase());
            builder.setImportance(importance);
        }

        var datePresent = !addTaskScreen.getDayField().getText().isEmpty();
        if (datePresent) {
            var day = Integer.parseInt(addTaskScreen.getDayField().getText());
            var month = Month.valueOf(addTaskScreen.getMonthChoice().getSelectedItem().toUpperCase());
            var year = Integer.parseInt(addTaskScreen.getYearChoice().getSelectedItem());

            try {
                builder.setDueDate(LocalDate.of(year, month, day));
            } catch (DateTimeException ex) {
                // TODO on invalid date popup maybe ??
                ex.printStackTrace();
            }
        }

        var creationDate = LocalDate.now();

        builder.setCompleted(false).setCreationDate(creationDate).setId(mainScreen.nextAvailableId());

        mainScreen.addTask(builder.build());
        // TODO reset fields
    }

}