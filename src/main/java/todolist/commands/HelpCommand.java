package todolist.commands;

public class HelpCommand implements Command {

	@Override
	public void execute() {
		// add : add a task
		// ls : list all tasks
		// rm : remove a task
		// edit : edit a task
		// done : set a task as done

	}

	public String name() {
		return "help";
	}

	public String usage() {
		return "Print the help message corresponding to the command asked.";
	}
}
