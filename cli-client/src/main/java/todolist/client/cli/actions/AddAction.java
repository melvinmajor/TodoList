package todolist.client.cli.actions;

import todolist.client.cli.util.PromptUtil;
import todolist.common.Command;
import todolist.common.TaskBuilder;

import java.time.LocalDate;

import static todolist.client.cli.parsing.Parsers.*;

public class AddAction implements Action {
    @Override
    public String getName() {
        return "add";
    }

    @Override
    public boolean execute(Data data) {
        var builder = new TaskBuilder();

        new PromptUtil<>(stringParser)
                .ask("Enter a description")
                .getOptional()
                .ifPresent(builder::setDescription);

        new PromptUtil<>(dateParser)
                .ask("Enter due date")
                .canIgnore()
                .getOptional()
                .ifPresent(builder::setDueDate);

        new PromptUtil<>(importanceParser)
                .ask("Enter importance")
                .canIgnore()
                .getOptional()
                .ifPresent(builder::setImportance);

        builder.setId(data.nextAvailableId)
                .setCreationDate(LocalDate.now())
                .build();

        if (!builder.validate()) return false;

        data.editedTask = builder.build();

        System.out.println("Task created");
        return true;
    }

    @Override
    public Command command() {
        return Command.ADD;
    }
}
