package todolist.client.gui.views;

import todolist.client.gui.GuiClient;
import todolist.common.Command;
import todolist.common.Packet;

public class DeleteView extends EditView {

    public DeleteView() {
        super("Delete", t -> GuiClient.instance.sendPacket(new Packet(Command.REMOVE, t)));
    }

}
