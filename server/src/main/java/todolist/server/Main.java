package todolist.server;

import argparsing.ArgParser;

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

        var saveFlg = newFlag("s", "save");
        var portOpt = newOption("p", "port");
        new ArgParser(portOpt, saveFlg)
                .addFileSource(pathString)
                .addSource(args)
                .parse();

        if (saveFlg.isPresent()) {
            var argsWithoutSaveFlag = Arrays.stream(args)
                    .filter(s -> !s.equals("-s") && !s.equals("--save"))
                    .collect(Collectors.joining(" "));

            try {
                Files.writeString(configPath, argsWithoutSaveFlag);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        int port = portOpt.getOptionalInt().orElse(8002);

        new Server(port).run();
    }
}
