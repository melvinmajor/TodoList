package todolist.commands;

public class AddCommand implements Command {

	@Override
	// Command to add a task
	public void addCommand(Task e) {
	    this.Task.add(e);
	}
	
    @Override
    public void execute() {
        // TODO Auto-generated method stub

    }

    public String name() {
        return "add";
    }

    public String usage() {
        return "Add a task.";
    }
}
