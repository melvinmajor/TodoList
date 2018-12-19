package todolist.client.cli.actions;

import todolist.common.Command;
import todolist.common.Task;
import todolist.common.TaskBuilder;

import static org.fusesource.jansi.Ansi.ansi;

public class CompleteAction extends EditAction {
    @Override
    public String getName() {
        return "complete";
    }

    @Override
    protected Task editTask(Task task) {
        System.out.println(ansi().fgGreen().a("Task completed"));
        return new TaskBuilder(task).setCompleted(true).build();
    }

    @Override
    public Command command() {
        return Command.EDIT;
    }

    @Override
    public String usage() {
        return "Complete a task";
    }
}
