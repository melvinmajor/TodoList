package todolist.client.cli;

import todolist.client.base.BaseClient;
import todolist.client.cli.actions.*;
import todolist.client.cli.parsing.Type;
import todolist.client.cli.util.CliUtil;
import todolist.client.cli.util.PromptResult.State;
import todolist.common.Query;
import todolist.common.Task;

import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class CLIClient extends BaseClient {
    private static final Action helpAction = new HelpAction();
    public static final List<Action> actions = List.of(
            helpAction,
            new ListAction(),
            new AddAction(),
            new ExitAction(),
            new RemoveAction(),
            new CompleteAction());

    public static final CLIClient instance = new CLIClient();

    private CLIClient() {
    }

    private boolean shouldExit;
    private CliUtil cliUtil;

    @Override
    public void run() {
        super.run();

        var scanner = new Scanner(System.in);
        cliUtil = new CliUtil(scanner);

        while (!shouldExit) {
            var actionResult = cliUtil.<Action>promptNoIgnore("Enter command", Type.ACTION);

            if (actionResult.state == State.EXIT) {
                disconnect();
                onExit();
                break;
            }

            execute(actionResult.value);
        }

    }

    @Override
    public void onConnectionError() {
        System.err.println("An error occurred while attempting to connect to the server");
        System.err.println("Is the server running ?");
        System.exit(1);
    }

    @Override
    public void onExit() {
        System.exit(0);
    }

    private void execute(Action action) {
        var data = new Data(nextAvailableId(), cliUtil);
        boolean success = action.execute(data);

        if (!success) {
            System.err.println("An error occured...");
            return;
        }

        if (action.command() != null) {
            var task = data.editedTask;
            var command = action.command();
            sendQuery(new Query(command, task));
        }

        if (data.closeClient) {
            disconnect();
            shouldExit = true;
        }
    }

    public Collection<Task> getTasks() {
        return tasks;
    }

}
