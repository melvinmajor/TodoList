package todolist.cli.actions;

import todolist.common.Command;

public interface Action {

    String getName();

    // return true on success
    boolean execute(Data data);

    Command command();

}
