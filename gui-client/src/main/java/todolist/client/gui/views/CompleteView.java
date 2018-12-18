package todolist.client.gui.views;

import todolist.client.gui.GuiClient;
import todolist.common.Command;
import todolist.common.Packet;
import todolist.common.TaskBuilder;

public class CompleteView extends EditView {

    public CompleteView() {
        super("Complete", t -> {
            var task = new TaskBuilder(t).setCompleted(true).build();
            GuiClient.instance.sendPacket(new Packet(Command.EDIT, task));
        });
    }

}
