package todolist.client.gui;

public class Main {

    /**
     * Initialize the GUI Client.
     *
     * @param args
     */
    public static void main(String[] args) {
        GuiClient.instance.setPort(8002);
        GuiClient.instance.run();
    }

}
