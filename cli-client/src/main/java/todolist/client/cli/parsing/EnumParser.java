package todolist.client.cli.parsing;

import java.util.Collection;
import java.util.List;

public class EnumParser<T extends Enum> extends LimitedSetParser<T> {

    private final Class<T> enumClass;

    public EnumParser(Class<T> enumClass) {
        this.enumClass = enumClass;
    }

    @Override
    protected String toName(T e) {
        return e.name().toLowerCase();
    }

    @Override
    protected Collection<T> elements() {
        return List.of(enumClass.getEnumConstants());
    }
}
