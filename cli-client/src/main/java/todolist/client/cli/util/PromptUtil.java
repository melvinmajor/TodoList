package todolist.client.cli.util;

import todolist.client.cli.CLIClient;
import todolist.client.cli.parsing.ParseException;
import todolist.client.cli.parsing.Parser;

import java.util.Objects;
import java.util.Optional;

import static org.fusesource.jansi.Ansi.ansi;

/**
 * Util class used in order to obtain a value from the user and then convert it with a parser
 */
public class PromptUtil<T> {
    private final Parser<T> parser;
    private String promptMsg;
    private boolean canIgnore;

    public PromptUtil(Parser<T> parser) {
        this.parser = parser;
    }

    public Optional<T> getOptional() {
        Objects.requireNonNull(parser, "A parser is required");
        Objects.requireNonNull(promptMsg, "A msg is required");

        var scanner = CLIClient.instance.scanner;

        T parsed;

        var msg = "@|blue " + promptMsg + "|@";
        if (canIgnore) msg += "@|yellow (optional)|@";
        msg += "@|blue > |@";

        while (true) {
            System.out.print(ansi().render(msg));
            if (!scanner.hasNextLine()) return Optional.empty();

            String input = scanner.nextLine().replaceAll(" +", " ");
            boolean ignored = input.isBlank();
            if (ignored && canIgnore) return Optional.empty();

            try {
                parsed = parser.parse(input);
                break;
            } catch (ParseException e) {
                System.err.println(ansi().render(parser.onError()));
            }
        }

        return Optional.ofNullable(parsed);
    }

    public PromptUtil<T> ask(String msg) {
        this.promptMsg = msg;
        return this;
    }

    public PromptUtil<T> canIgnore() {
        this.canIgnore = true;
        return this;
    }


}
