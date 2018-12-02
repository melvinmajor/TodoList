package todolist.client.cli.util;

import java.util.Scanner;
import java.util.function.Function;

public class CliUtil {
    private final Scanner scanner;

    public CliUtil(Scanner scanner) {
        this.scanner = scanner;
    }

    private PromptResult<String> promptOnce(String s) {
        return promptOnce(s, Function.identity());
    }

    private <T> PromptResult<T> promptOnce(String s, Function<String, T> parse) {
        System.out.print(s + "> ");
        if (!scanner.hasNextLine()) return PromptResult.exited();

        var input = scanner.nextLine().trim().replaceAll(" +", " ");

        if (input.toLowerCase().equals("exit"))
            return PromptResult.exited();
        else if (input.isEmpty())
            return PromptResult.ignored();

        return new PromptResult<>(parse.apply(input));
    }

    private PromptResult<String> promptRepeat(String s, boolean canIgnore) {
        return promptRepeat(s, e -> e.isEmpty() ? null : e, canIgnore);
    }

    private <T> PromptResult<T> promptRepeat(String s, Function<String, T> parse, boolean canIgnore) {
        while (true) {
            PromptResult<T> val = promptOnce(s, parse);
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

    public <T> PromptResult<T> promptIgnore(String s, Function<String, T> parse) {
        return promptRepeat(s, parse, true);
    }

    public <T> PromptResult<T> promptNoIgnore(String s, Function<String, T> parse) {
        return promptRepeat(s, parse, false);
    }

    public PromptResult<String> promptIgnore(String s) {
        return promptRepeat(s, Function.identity(), true);
    }

    public PromptResult<String> promptNoIgnore(String s) {
        return promptRepeat(s, Function.identity(), false);
    }

}
