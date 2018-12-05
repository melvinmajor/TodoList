package todolist.client.cli.util;

public class PromptResult<T> {
    public final T value;
    public final State state;

    private PromptResult(State state) {
        this.value = null;
        this.state = state;
    }

    PromptResult(T value) {
        this.value = value;
        this.state = value == null ? State.FAILURE : State.SUCCESS;
    }


    private static final PromptResult exitedResult = new PromptResult(State.EXIT);
    private static final PromptResult ignoredResult = new PromptResult(State.IGNORE);

    static <T> PromptResult<T> exited() {
        return (PromptResult<T>) exitedResult;
    }

    static <T> PromptResult<T> ignored() {
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
