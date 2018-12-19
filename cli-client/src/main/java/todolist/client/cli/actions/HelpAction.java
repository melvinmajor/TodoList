package todolist.client.cli.actions;

import todolist.client.cli.CLIClient;
import todolist.common.Command;

import java.util.stream.Collectors;

import static org.fusesource.jansi.Ansi.ansi;

public class HelpAction implements Action {
    @Override
    public String getName() {
        return "help";
    }

    @Override
    public boolean execute(Data data) {
        String actions = CLIClient.actions.stream()
                .map(Action::getName)
                .map(s -> ansi().fgBrightMagenta().a(s).reset().toString())
                .collect(Collectors.joining(" | "));

        System.out.println(ansi().render("Available commands: { " + actions + " }\n"));

        CLIClient.actions.stream()
                .map(a -> ansi().a("Where ").fgBrightBlue().a(a.getName()).reset().a(" ").a(a.usage()).toString())
                .map(s -> ansi().render(s))
                .forEach(System.out::println);

        return true;
    }

    @Override
    public Command command() {
        return null;
    }

    @Override
    public String usage() {
        return "Print this help message";
    }

}
