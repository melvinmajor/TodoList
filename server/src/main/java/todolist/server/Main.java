package todolist.server;

import argparsing.ArgParser;
import argparsing.Option;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Collectors;

import static argparsing.OptionFactory.newFlag;
import static argparsing.OptionFactory.newOption;


public class Main {
    /**
     * the main method.
     *
     * @param args the args, --port or -p followed by a port number
     *             and -s or --save to save other args
     */
    public static void main(String[] args) {
        var pathString = "server-config.cfg";
        var configPath = Path.of(pathString);

        var helpFlg = newFlag("h", "help");
        var saveFlg = newFlag("s", "save");
        var portOpt = newOption("p", "port");
        new ArgParser(helpFlg, portOpt, saveFlg)
                .addFileSource(pathString)
                .addSource(args)
                .parse();

        if (helpFlg.isPresent()) {
            System.out.println("Available commands:\n");
            System.out.println(getHelpString(helpFlg) + " : Print this message");
            System.out.println(getHelpString(saveFlg) + " : Save arguments to a file for next time");
            System.out.println(getHelpString(portOpt) + " : Set the server port");
            return;
        }

        if (saveFlg.isPresent()) {
            var argsWithoutSaveFlag = Arrays.stream(args)
                    .filter(s -> !s.equals("-s") && !s.equals("--save"))
                    .collect(Collectors.joining(" "));

            try {
                Files.writeString(configPath, argsWithoutSaveFlag);
                Server.logger.info("saved '" + argsWithoutSaveFlag + "' to " + pathString);
            } catch (IOException e) {
                Server.logger.error(e.getMessage());
            }
        }

        int port = portOpt.getOptionalInt().orElse(8002);

        Server.logger.info("Starting the server @ " + port);

        new Server(port).run();
    }

    private static String getHelpString(Option opt) {
        if (opt.shortName != null && opt.longName == null) return opt.shortName;
        if (opt.longName != null && opt.shortName == null) return opt.longName;

        return opt.shortName + " | " + opt.longName;
    }

}
