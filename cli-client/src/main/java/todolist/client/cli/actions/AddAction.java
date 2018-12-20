package todolist.client.cli.actions;

import todolist.client.cli.util.PromptUtil;
import todolist.common.Command;
import todolist.common.TaskBuilder;

import java.time.DateTimeException;
import java.time.LocalDate;

import static org.fusesource.jansi.Ansi.ansi;
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
        var now = LocalDate.now();

        while (true) {
            var dayOptional = new PromptUtil<>(dayParser)
                    .ask("Enter due day")
                    .canIgnore()
                    .getOptional();

            if (dayOptional.isEmpty()) break;

            var day = dayOptional.get();

            var monthOptional = new PromptUtil<>(monthParser)
                    .ask("Enter month")
                    .canIgnore()
                    .getOptional();

            var month = monthOptional.orElse(now.getMonth());

            int year;
            if (monthOptional.isPresent()) year = new PromptUtil<>(yearParser)
                    .ask("Enter year")
                    .canIgnore()
                    .getOptional()
                    .orElse(now.getYear());
            else year = now.getYear();

            try {
                var dueDate = LocalDate.of(year, month, day);
                builder.setDueDate(dueDate);
                break;
            } catch (DateTimeException e) {
                // continue loop
            }

        }


        new PromptUtil<>(importanceParser)
                .ask("Enter importance")
                .canIgnore()
                .getOptional()
                .ifPresent(builder::setImportance);

        builder.setId(data.nextAvailableId).build();

        if (!builder.validate()) return false;

        data.editedTask = builder.build();

        System.out.println(ansi().fgGreen().a("Task created"));
        return true;
    }

    @Override
    public Command command() {
        return Command.ADD;
    }

    @Override
    public String usage() {
        return "Add a task";
    }
}
