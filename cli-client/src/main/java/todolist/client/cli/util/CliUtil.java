package todolist.client.cli.util;

import todolist.client.cli.parsing.Parsers;
import todolist.client.cli.parsing.Type;

import java.util.Scanner;

public class CliUtil {
    private final Scanner scanner;

    public CliUtil(Scanner scanner) {
        this.scanner = scanner;
    }

    private <T> PromptResult<T> promptOnce(String s, Type type) {
        System.out.print(s + "> ");
        if (!scanner.hasNextLine()) return PromptResult.exited();

        var input = scanner.nextLine().trim().replaceAll(" +", " ");

        if (input.toLowerCase().equals("exit"))
            return PromptResult.exited();
        else if (input.isEmpty())
            return PromptResult.ignored();

        T value = Parsers.parse(input, type);

        return new PromptResult<>(value);
    }


    private <T> PromptResult<T> promptRepeat(String s, Type type, boolean canIgnore) {
        while (true) {
            PromptResult<T> val = promptOnce(s, type);
            switch (val.state) {
                case EXIT:
                    return val;
                case IGNORE:
                    if (canIgnore) return val;
                    break;
                case SUCCESS:
                    return val;
            }
        }
    }

    public <T> PromptResult<T> promptIgnore(String s, Type type) {
        return promptRepeat(s, type, true);
    }

    public <T> PromptResult<T> promptNoIgnore(String s, Type type) {
        return promptRepeat(s, type, false);
    }

}