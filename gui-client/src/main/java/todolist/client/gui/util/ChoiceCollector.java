package todolist.client.gui.util;

import java.awt.*;
import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * implementation of the {@code Collector} interface that accumulates the input elements
 * into a new {@code Choice}.
 */
public class ChoiceCollector implements Collector<String, Choice, Choice> {

    @Override
    public Supplier<Choice> supplier() {
        return Choice::new;
    }

    @Override
    public BiConsumer<Choice, String> accumulator() {
        return Choice::add;
    }

    @Override
    public BinaryOperator<Choice> combiner() {
        return null;
    }

    @Override
    public Function<Choice, Choice> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.singleton(Characteristics.IDENTITY_FINISH);
    }

}