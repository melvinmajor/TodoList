package todolist.clients.cli;

import todolist.clients.BaseClient;
import todolist.clients.cli.actions.*;
import todolist.clients.cli.util.ArgParser;
import todolist.common.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CLIClient extends BaseClient {
    private static final Action helpAction = new HelpAction();
    public static final List<Action> actions = List.of(
            helpAction,
            new ListAction(),
            new AddAction(),
            new ExitAction(),
            new RemoveAction());

    private boolean shouldExit;

    @Override
    public void run() {
        super.run();
        // TODO
        Scanner scan = new Scanner(System.in);
        do {
            System.out.print("\ntodolist> ");

            if (!scan.hasNextLine()) {
                disconnect();
                onExit();
                break;
            }

            var input = scan.nextLine().trim().replaceAll(" +", " ");

            execute(input);

        } while (!shouldExit);
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

    private void execute(String input) {
        String[] strings = input.split(" ");

        Action action = parse(strings[0]);

        var args = new ArrayList<>(Arrays.asList(strings));
        args.remove(strings[0]);

        Data data = new Data(tasks, args, nextAvailableId());
        boolean success = action.execute(data);

        if (!success) {
            System.err.println("An error occured..");
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

    private Action parse(String action) {
        Action match = ArgParser.match(actions, Action::getName, action);
        return match == null ? helpAction : match;
    }

}
