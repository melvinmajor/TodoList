package todolist.commands;

public class RemoveCommand implements Command {

	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

	public String name() {
		return "rm";
	}

	public String usage() {
		return "Remove a task.";
	}
}
