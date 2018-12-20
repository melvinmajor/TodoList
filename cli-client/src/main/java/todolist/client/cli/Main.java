package todolist.client.cli;

import argparsing.ArgParser;
import org.fusesource.jansi.AnsiConsole;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Collectors;

import static argparsing.OptionFactory.newFlag;
import static argparsing.OptionFactory.newOption;

public class Main {
    public static void main(String[] args) {
        AnsiConsole.systemInstall();

        var pathString = "cli-client-config.cfg";
        var configPath = Path.of(pathString);

        var saveFlg = newFlag("s", "save");
        var portOpt = newOption("p", "port");
        var hostOpt = newOption("h", "host");
        new ArgParser(portOpt, hostOpt, saveFlg)
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
        var host = hostOpt.getOptional().orElse("localhost");

        var client = CLIClient.instance;
        client.setPort(port);
        client.setHost(host);
        client.run();
    }
}
