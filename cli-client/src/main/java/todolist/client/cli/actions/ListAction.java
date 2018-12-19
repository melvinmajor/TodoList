package todolist.client.cli.actions;

import todolist.client.cli.CLIClient;
import todolist.client.cli.util.DisplayString;
import todolist.client.cli.util.TablePrinter;
import todolist.common.Command;
import todolist.common.Task;

import java.util.ArrayList;
import java.util.List;

import static org.fusesource.jansi.Ansi.ansi;

public class ListAction implements Action {

    @Override
    public String getName() {
        return "list";
    }

    @Override
    public boolean execute(Data data) {
        var tasks = CLIClient.instance.getTasks();

        if (tasks.isEmpty()) {
            System.out.println(ansi().fgRed().a("There are no tasks!"));
            return true;
        }

        var header = List.of("Description", "Due", "Importance", "Completed");

        List<List<String>> content = new ArrayList<>();
        for (Task task : tasks) {
            var innerList = new ArrayList<String>();
            content.add(innerList);

            innerList.add(task.description);

            var date = DisplayString.of(task.dueDate);
            innerList.add(date);

            var importance = DisplayString.of(task.importance);
            innerList.add(importance);

            var completed = DisplayString.of(task.completed);
            innerList.add(completed);
        }

        new TablePrinter(header, content).print();

        return true;
    }

    @Override
    public Command command() {
        return null;
    }

    @Override
    public String usage() {
        return "List all tasks";
    }

}
