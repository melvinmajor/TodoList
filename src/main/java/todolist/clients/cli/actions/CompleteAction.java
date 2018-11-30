package todolist.clients.cli.actions;

import todolist.common.Command;
import todolist.common.Task;
import todolist.common.TaskBuilder;

import java.util.List;

public class CompleteAction extends BaseIdAction {
    @Override
    public String getName() {
        return "complete";
    }

    @Override
    protected Task edit(Task task, List<String> args) {
        return new TaskBuilder(task).setCompleted(true).build();
    }

    @Override
    public Command command() {
        return Command.EDIT;
    }
}
