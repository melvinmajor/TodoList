package todolist.cli.actions;

import todolist.server.common.Command;

public interface Action {

    String getName();

    boolean execute(Data data);

    Command command();

}
