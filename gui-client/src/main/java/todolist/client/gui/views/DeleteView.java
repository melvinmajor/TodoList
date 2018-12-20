package todolist.client.gui.views;

import todolist.client.gui.GuiClient;
import todolist.common.Command;
import todolist.common.Packet;

    /**
     *  This class is use by the user to delete a task
     */

public class DeleteView extends EditView {
    // The command Command.REMOVE, remove the task selected
    public DeleteView() {
        super("Delete", t -> GuiClient.instance.sendPacket(new Packet(Command.REMOVE, t)));
    }

}
