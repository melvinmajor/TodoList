package todolist.commands;

public class RemoveCommand implements Command {

    @Override
		// Command to remove a task
    public void removeCommand(Task e) {
		    this.Task.remove(e);
	  }

    @Override
    public void execute() {
        // TODO Auto-generated method stub

    public String name() {
        return "rm";
    }

    public String usage() {
        return "Remove a task.";
    }
}
