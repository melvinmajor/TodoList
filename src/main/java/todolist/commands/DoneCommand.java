package todolist.commands;

public class DoneCommand extends Command {

    @Override
    public void execute() {
        // TODO Auto-generated method stub

    }

    public String name() {
        return "done";
    }

    public String usage() {
        return "Set a task as done.";
    }
}
