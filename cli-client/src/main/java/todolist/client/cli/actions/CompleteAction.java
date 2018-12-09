package todolist.client.cli.actions;

import todolist.common.Command;
import todolist.common.Task;
import todolist.common.TaskBuilder;

public class CompleteAction extends EditAction {
    @Override
    public String getName() {
        return "complete";
    }

    @Override
    protected Task editTask(Task task) {
        System.out.println("Task completed");
        return new TaskBuilder(task).setCompleted(true).build();
    }

    @Override
    public Command command() {
        return Command.EDIT;
    }
}
