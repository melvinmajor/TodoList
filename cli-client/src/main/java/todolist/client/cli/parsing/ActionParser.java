package todolist.client.cli.parsing;

import todolist.client.cli.CLIClient;
import todolist.client.cli.actions.Action;

import java.util.Collection;

class ActionParser extends LimitedSetParser<Action> {
    @Override
    protected String toName(Action e) {
        return e.getName();
    }

    @Override
    protected Collection<Action> elements() {
        return CLIClient.actions;
    }

    @Override
    public Type type() {
        return Type.ACTION;
    }
}
