package todolist.commands;

import java.io.Serializable;

public abstract class Command implements Serializable {
    public abstract void execute();

    public abstract String name();

    public abstract String usage();
}
