package todolist.commands;

public interface Command {
	public void execute();

	public String name();

	public String usage();
}
