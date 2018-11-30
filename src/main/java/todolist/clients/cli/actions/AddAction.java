package todolist.clients.cli.actions;

import todolist.clients.cli.util.ParseUtil;
import todolist.clients.cli.util.PromptResult.State;
import todolist.common.Command;
import todolist.common.Importance;
import todolist.common.TaskBuilder;

import java.time.LocalDate;
import java.util.List;

public class AddAction implements Action {
    @Override
    public String getName() {
        return "add";
    }

    @Override
    public boolean execute(Data data) {
        var cli = data.cliUtil;
        var builder = new TaskBuilder();

        var descriptionResult = cli.promptNoIgnore("Enter description");
        if (descriptionResult.state == State.EXIT) return false;
        builder.setDescription(descriptionResult.value);

        var dueDateResult = cli.promptIgnore("Enter due date (optional)", ParseUtil::parseDate);
        if (dueDateResult.state == State.EXIT) return false;
        else if (dueDateResult.state == State.SUCCESS) builder.setDueDate(dueDateResult.value);

        var importanceResult = cli.promptIgnore("Enter importance (optional)",
                e -> ParseUtil.match(List.of(Importance.values()), Importance::name, e));
        if (importanceResult.state == State.EXIT) return false;
        else if (importanceResult.state == State.SUCCESS) builder.setImportance(importanceResult.value);

        data.editedTask = builder
                .setId(data.nextAvailableId)
                .setCreationDate(LocalDate.now())
                .build();

        System.out.println("Task created");
        return true;
    }

    @Override
    public Command command() {
        return Command.ADD;
    }
}
