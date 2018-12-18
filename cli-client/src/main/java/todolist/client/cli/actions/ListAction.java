package todolist.client.cli.actions;

import todolist.client.cli.CLIClient;
import todolist.client.cli.util.TablePrinter;
import todolist.common.Command;
import todolist.common.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class ListAction implements Action {
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");

    @Override
    public String getName() {
        return "ls";
    }

    @Override
    public boolean execute(Data data) {
        var tasks = CLIClient.instance.getTasks();

        if (tasks.isEmpty()) {
            System.out.println("There is no tasks!");
            return true;
        }

        var header = List.of("Description", "Due", "Importance", "Completed");

        var now = LocalDate.now();

        List<List<String>> content = new ArrayList<>();
        for (Task task : tasks) {
            var innerList = new ArrayList<String>();
            content.add(innerList);

            innerList.add(task.description);

            var date = "";

            if (task.dueDate != null) {
                date += task.dueDate.format(dateFormatter);
                var diff = ChronoUnit.DAYS.between(now, task.dueDate);
                date += " (";
                if (diff == 0) {
                    date += "today";
                } else if (diff == 1) {
                    date += "tomorrow";
                } else if (diff > 0) {
                    date += "in " + diff + " days";
                } else if (diff == -1) {
                    date += "yesterday";
                } else {
                    diff = Math.abs(diff);
                    date += diff + " days ago";
                }
                date += ")";
            }

            innerList.add(date);

            var importance = task.importance == null ? "" : task.importance.name().toLowerCase();
            innerList.add(importance);

            var completed = task.completed ? "V" : "X";
            innerList.add(completed);
        }

        new TablePrinter(header, content).print();

        return true;
    }

    @Override
    public Command command() {
        return null;
    }

}
