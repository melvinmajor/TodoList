package todolist.clients.cli.util;

public class PromptResult<T> {
    public final T value;
    public final State state;

    private PromptResult(T value, State state) {
        this.value = value;
        this.state = state;
    }

    public PromptResult(T value) {
        this.value = value;
        this.state = value == null ? State.FAILURE : State.SUCCESS;
    }


    private static final PromptResult exitedResult = new PromptResult(null, State.EXIT);
    private static final PromptResult ignoredResult = new PromptResult(null, State.IGNORE);

    public static <T> PromptResult<T> exited() {
        return (PromptResult<T>) exitedResult;
    }

    public static <T> PromptResult<T> ignored() {
        return (PromptResult<T>) ignoredResult;
    }

    @Override
    public String toString() {
        return "PromptResult{" +
                "value=" + value +
                ", state=" + state +
                '}';
    }

    public enum State {
        EXIT, IGNORE, SUCCESS, FAILURE
    }
}
