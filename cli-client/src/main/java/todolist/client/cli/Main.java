package todolist.client.cli;

import argparsing.ArgParser;
import argparsing.Option;
import org.fusesource.jansi.AnsiConsole;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.stream.Collectors;

import static argparsing.OptionFactory.newFlag;
import static argparsing.OptionFactory.newOption;
import static org.fusesource.jansi.Ansi.ansi;

public class Main {
    public static void main(String[] args) {
        AnsiConsole.systemInstall();

        var pathString = "cli-client-config.cfg";
        var configPath = Path.of(pathString);

        var helpFlg = newFlag("h", "help");
        var saveFlg = newFlag("s", "save");
        var portOpt = newOption("p", "port");
        var hostOpt = newOption("H", "host");
        new ArgParser(helpFlg, portOpt, hostOpt, saveFlg)
                .addFileSource(pathString)
                .addSource(args)
                .parse();

        if (helpFlg.isPresent()) {
            System.out.println("Available commands:\n");
            System.out.println(getHelpString(helpFlg) + " : Print this message");
            System.out.println(getHelpString(saveFlg) + " : Save arguments to a file for next time");
            System.out.println(getHelpString(portOpt) + " : Set the server port");
            System.out.println(getHelpString(hostOpt) + " : Set the server host");
            return;
        }


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

    private static String getHelpString(Option opt) {
        if (opt.shortName != null && opt.longName == null) return singleHelpString(opt.shortName);
        if (opt.longName != null && opt.shortName == null) return singleHelpString(opt.longName);

        return ansi().fgGreen().a(opt.shortName).reset().a(" | ").fgGreen().a(opt.longName).reset().toString();
    }

    private static String singleHelpString(String str) {
        return ansi().fgGreen().a(str).reset().toString();
    }
}
