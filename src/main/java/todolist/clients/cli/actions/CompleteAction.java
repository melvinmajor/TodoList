package todolist.clients.cli.actions;

import todolist.clients.cli.util.ParseUtil;
import todolist.clients.cli.util.PromptResult;
import todolist.clients.cli.util.PromptResult.State;
import todolist.clients.cli.util.TaskUtil;
import todolist.common.Command;
import todolist.common.Task;
import todolist.common.TaskBuilder;

import java.util.function.Function;

public class CompleteAction implements Action {
    @Override
    public String getName() {
        return "complete";
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

        data.editedTask = new TaskBuilder(result.value).setCompleted(true).build();

        System.out.println("Task completed");
        return true;
    }

    @Override
    public Command command() {
        return Command.EDIT;
    }
}
