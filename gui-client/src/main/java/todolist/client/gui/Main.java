package todolist.client.gui;

import java.util.Locale;

public class Main {

    /**
     * Initialize the GUI Client.
     *
     * @param args
     */
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        GuiClient.instance.setPort(8002);
        GuiClient.instance.run();
    }

}
