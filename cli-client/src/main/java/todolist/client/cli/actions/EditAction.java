package todolist.client.cli.actions;

import todolist.client.cli.CLIClient;
import todolist.client.cli.parsing.TaskByIdParser;
import todolist.client.cli.util.PromptUtil;
import todolist.client.cli.util.TablePrinter;
import todolist.common.Task;

import java.util.ArrayList;
import java.util.List;

import static org.fusesource.jansi.Ansi.ansi;

public abstract class EditAction implements Action {
    @Override
    public final boolean execute(Data data) {
        var tasks = CLIClient.instance.getTasks();

        if (tasks.isEmpty()) {
            System.out.println(ansi().fgRed().a("There are no tasks!"));
            return false;
        }

        // Not the same ID as the one in the actual task
        var header = List.of("ID", "Description");
        var content = new ArrayList<List<String>>();

        // convert to array for index since tasks is a collection
        Task[] taskArray = tasks.toArray(new Task[0]);

        for (int i = 0; i < taskArray.length; i++) {
            var innerList = new ArrayList<String>();
            content.add(innerList);

            innerList.add(String.valueOf(i));
            innerList.add(taskArray[i].description);
        }

        new TablePrinter(header, content).print();

        var parser = new TaskByIdParser(taskArray);
        var oldTask = new PromptUtil<>(parser)
                .ask("Enter an id")
                .getOptional()
                .get();

        Task editedTask = editTask(oldTask);

        if (editedTask == null) return false;

        data.editedTask = editedTask;
        return true;
    }

    protected abstract Task editTask(Task task);
}
