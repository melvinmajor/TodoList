package todolist;

import java.util.HashMap;
import java.util.Map;

import todolist.commands.AddCommand;
import todolist.commands.Command;
import todolist.commands.DoneCommand;
import todolist.commands.EditCommand;
import todolist.commands.HelpCommand;
import todolist.commands.ListCommand;
import todolist.commands.RemoveCommand;

public class ArgParser {
	private static Map<String, Command> commandMap = new HashMap<>();

	static {
		commandMap.put("add", new AddCommand());
		commandMap.put("ls", new ListCommand());
		commandMap.put("rm", new RemoveCommand());
		commandMap.put("edit", new EditCommand());
		commandMap.put("done", new DoneCommand());
		commandMap.put("help", new HelpCommand());
	}

	public static Command parse(String[] args) {
		if (args.length == 0)
			return commandMap.get("help");

		Command command = commandMap.get(args[0].toLowerCase());
		if (command != null)
			return command;
		return commandMap.get("help");
	}
}
