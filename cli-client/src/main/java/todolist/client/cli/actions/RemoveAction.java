package todolist.client.cli.actions;

import todolist.client.cli.parsing.Type;
import todolist.client.cli.util.PromptResult;
import todolist.client.cli.util.PromptResult.State;
import todolist.common.Command;
import todolist.common.Task;

public class RemoveAction implements Action {
    @Override
    public String getName() {
        return "rm";
    }

    @Override
    public boolean execute(Data data) {
        PromptResult<Task> result = data.cliUtil.promptNoIgnore("Enter an id", Type.ID);
        if (result.state == State.EXIT) return false;

        data.editedTask = result.value;
        System.out.println("Task removed");
        return true;
    }

    @Override
    public Command command() {
        return Command.REMOVE;
    }
}
