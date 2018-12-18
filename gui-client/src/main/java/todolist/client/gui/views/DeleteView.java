package todolist.client.gui.views;

import todolist.client.gui.GuiClient;
import todolist.common.Command;
import todolist.common.Query;

public class DeleteView extends EditView {

    public DeleteView() {
        super("Delete", t -> GuiClient.instance.sendQuery(new Query(Command.REMOVE, t)));
    }

}
