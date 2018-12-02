package todolist.client.cli.actions;

import todolist.client.cli.util.ParseUtil;
import todolist.client.cli.util.PromptResult;
import todolist.client.cli.util.PromptResult.State;
import todolist.client.cli.util.TaskUtil;
import todolist.common.Command;
import todolist.common.Task;

import java.util.function.Function;

public class RemoveAction implements Action {
    @Override
    public String getName() {
        return "rm";
    }

    @Override
    public boolean execute(Data data) {
        Function<String, Task> taskById = str -> {
            var id = ParseUtil.getInt(str);
            if (id == null) return null;
            return TaskUtil.getTaskById(data.tasks, id);
        };

        PromptResult<Task> result = data.cliUtil.promptNoIgnore("Enter an id", taskById);
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
