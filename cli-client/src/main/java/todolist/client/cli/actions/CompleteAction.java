package todolist.client.cli.actions;

import todolist.client.cli.parsing.Type;
import todolist.client.cli.util.PromptResult;
import todolist.client.cli.util.PromptResult.State;
import todolist.common.Command;
import todolist.common.Task;
import todolist.common.TaskBuilder;

public class CompleteAction implements Action {
    @Override
    public String getName() {
        return "complete";
    }

    @Override
    public boolean execute(Data data) {
        PromptResult<Task> result = data.cliUtil.promptNoIgnore("Enter an id", Type.ID);
        if (result.state == State.EXIT) return false;

        data.editedTask = new TaskBuilder(result.value).setCompleted(true).build();

        System.out.println("Task completed");
        return true;
    }

    @Override
    public Command command() {
        return Command.EDIT;
    }
}
