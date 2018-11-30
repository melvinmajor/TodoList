package todolist.clients.cli.actions;

import todolist.common.Command;

public interface Action {

    String getName();

    boolean execute(Data data);

    Command command();

}
