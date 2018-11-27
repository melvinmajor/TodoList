package todolist.commands;

import todolist.Main;
import todolist.Task;

public class ListCommand extends Command {

    @Override
    public void execute() {
    	var tasks = Main.taskManager.getTasks();
    	System.out.println("| Description | Importance | Due date | Categories | Users | Completed |");
    	for (Task t: tasks) {
    		System.out.printf("| %s | %s | %s | %s | %s | %b |\n", t.getDescription(), t.getImportance(), t.getDueDate(), t.getCategories(), t.getUsers(), t.isCompleted());
    	}
    }

    public String name() {
        return "ls";
    }

    public String usage() {
        return "List all tasks.";
    }
}
