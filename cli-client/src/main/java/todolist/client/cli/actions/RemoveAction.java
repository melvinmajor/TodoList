package todolist.client.cli.actions;

import todolist.common.Command;
import todolist.common.Task;

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
        System.out.println("Task removed");
        return task;
    }
}
