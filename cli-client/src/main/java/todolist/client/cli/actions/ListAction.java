package todolist.client.cli.actions;

import todolist.client.cli.CLIClient;
import todolist.client.cli.util.TablePrinter;
import todolist.common.Command;
import todolist.common.Task;

import java.util.ArrayList;
import java.util.List;

public class ListAction implements Action {
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

        List<List<String>> content = new ArrayList<>();
        for (Task task : tasks) {
            var innerList = new ArrayList<String>();
            content.add(innerList);

            innerList.add(task.description);

            var date = task.dueDate == null ? "" : task.dueDate.toString();
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