package todolist;

import todolist.commands.Command;

import java.io.Serializable;

public class Query implements Serializable {
    public final Command command;
    public final Task task;

    public Query(Command command, Task task) {
        this.command = command;
        this.task = task;
    }
}
