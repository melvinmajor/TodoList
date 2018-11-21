package todolist.commands;

public class ListCommand implements Command {

    @Override
    public void execute() {
        // TODO Auto-generated method stub

    }

    public String name() {
        return "ls";
    }

    public String usage() {
        return "List all tasks.";
    }
}
