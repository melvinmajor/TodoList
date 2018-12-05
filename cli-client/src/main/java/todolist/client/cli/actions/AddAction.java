package todolist.client.cli.actions;

import todolist.client.cli.parsing.Type;
import todolist.client.cli.util.PromptResult.State;
import todolist.common.Command;
import todolist.common.Importance;
import todolist.common.TaskBuilder;

import java.time.LocalDate;

public class AddAction implements Action {
    @Override
    public String getName() {
        return "add";
    }

    @Override
    public boolean execute(Data data) {
        var cli = data.cliUtil;
        var builder = new TaskBuilder();

        var descriptionResult = cli.<String>promptNoIgnore("Enter description", Type.STRING);
        if (descriptionResult.state == State.EXIT) return false;
        builder.setDescription(descriptionResult.value);

        var dueDateResult = cli.<LocalDate>promptIgnore("Enter due date (optional)", Type.DATE);
        if (dueDateResult.state == State.EXIT) return false;
        else if (dueDateResult.state == State.SUCCESS) builder.setDueDate(dueDateResult.value);

        var importanceResult = cli.<Importance>promptIgnore("Enter importance (optional)", Type.IMPORTANCE);
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
