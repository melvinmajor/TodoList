package todolist.clients.cli.actions;

import todolist.common.Command;

public class ExitAction implements Action{
    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public boolean execute(Data data) {
        data.closeClient = true;
        return true;
    }

    @Override
    public Command command() {
        return null;
    }
}
