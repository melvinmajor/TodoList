package todolist.client.cli.actions;

import todolist.common.Command;
import todolist.common.Task;

import static org.fusesource.jansi.Ansi.ansi;

public class RemoveAction extends EditAction {
    @Override
    public String getName() {
        return "remove";
    }

    @Override
    public Command command() {
        return Command.REMOVE;
    }

    @Override
    public String usage() {
        return "Remove a task";
    }

    @Override
    protected Task editTask(Task task) {
        System.out.println(ansi().fgGreen().a("Task removed"));
        return task;
    }
}
