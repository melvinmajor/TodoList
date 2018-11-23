package todolist.commands;

public class RemoveCommand implements Command {

	@Override
		// Command to remove a task
  public void removeCommand(Task e) {
		this.Task.remove(e);
	}
}



