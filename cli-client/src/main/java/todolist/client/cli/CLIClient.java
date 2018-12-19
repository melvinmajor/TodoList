package todolist.client.cli;

import todolist.client.base.BaseClient;
import todolist.client.cli.actions.*;
import todolist.client.cli.util.PromptUtil;
import todolist.common.Packet;
import todolist.common.Task;

import java.time.LocalDate;
import java.util.*;

import static org.fusesource.jansi.Ansi.ansi;
import static todolist.client.cli.parsing.Parsers.actionParser;

/**
 * Cli implementation of BaseClient
 */
public class CLIClient extends BaseClient {
    public static final CLIClient instance = new CLIClient();

    private final Comparator<Task> comparator = Comparator
            .<Task, LocalDate>comparing(t -> t.dueDate, Comparator.nullsLast(Comparator.naturalOrder()))
            .thenComparing(t -> t.importance, Comparator.nullsLast(Comparator.naturalOrder()));

    public static final List<Action> actions = List.of(
            new HelpAction(),
            new ListAction(),
            new AddAction(),
            new ExitAction(),
            new RemoveAction(),
            new CompleteAction());


    private CLIClient() {
    }

    private boolean shouldExit;

    @Override
    public void run() {
        super.run();

        var actionPrompt = new PromptUtil<>(actionParser).ask("Enter command");

        while (!shouldExit) {
            actionPrompt.getOptional().ifPresent(this::execute);
        }

    }

    @Override
    public void onConnectionError() {
        System.err.println(ansi().fgRed().a("An error occurred while attempting to connect to the server"));
        System.err.println(ansi().fgRed().a("Is the server running ?"));
        System.exit(1);
    }

    @Override
    public void onExit() {
        System.exit(0);
    }

    private void execute(Action action) {
        var data = new Data(nextAvailableId());
        boolean success = action.execute(data);

        if (!success) return;

        if (action.command() != null) {
            var task = data.editedTask;
            var command = action.command();
            sendPacket(new Packet(command, task));
        }

        if (data.closeClient) {
            disconnect();
            shouldExit = true;
        }
    }

    public Collection<Task> getTasks() {
        return tasks;
    }

    @Override
    public boolean onUpdate(Collection<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
        this.tasks.sort(comparator);
        return false;
    }
}
