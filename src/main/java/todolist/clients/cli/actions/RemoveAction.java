package todolist.clients.cli.actions;

import todolist.common.Command;
import todolist.common.Task;

import java.util.List;

public class RemoveAction extends BaseIdAction {
    @Override
    public String getName() {
        return "rm";
    }

    @Override
    protected Task edit(Task task, List<String> args) {
        return task;
    }

    @Override
    public Command command() {
        return Command.REMOVE;
    }
}
