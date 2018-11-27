package todolist.commands;

import todolist.Task;

import java.io.Serializable;

public abstract class Command implements Serializable {
    public abstract void execute(Task task);

    public abstract String name();

    public abstract String usage();
}
