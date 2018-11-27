package todolist.commands;

public class ExitServerCommand extends Command{
    @Override
    public void execute() {
    }

    @Override
    public String name() {
        return "exit";
    }

    @Override
    public String usage() {
        return "exit the server";
    }
}
