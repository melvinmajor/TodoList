package todolist.client.cli.actions;

import todolist.common.Command;
import todolist.common.Task;

import static org.fusesource.jansi.Ansi.ansi;

public class RemoveAction extends EditAction {
    @Override
    public String getName() {
        return "rm";
    }

    @Override
    public Command command() {
        return Command.REMOVE;
    }

    @Override
    protected Task editTask(Task task) {
        System.out.println(ansi().fgGreen().a("Task removed"));
        return task;
    }
}
