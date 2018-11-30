package todolist.clients.cli.actions;

import todolist.clients.cli.CLIClient;
import todolist.common.Command;

import java.util.stream.Collectors;

public class HelpAction implements Action {
    @Override
    public String getName() {
        return "help";
    }

    @Override
    public boolean execute(Data data) {
        String actions = CLIClient.actions.stream()
                .map(Action::getName)
                .collect(Collectors.joining(", "));

        System.out.println("Available actions: " + actions);

        return true;
    }

    @Override
    public Command command() {
        return null;
    }

}
