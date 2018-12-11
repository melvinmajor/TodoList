package todolist.client.cli.parsing;

import todolist.common.Importance;

import java.util.Collection;
import java.util.List;

public class ImportanceParser extends LimitedSetParser<Importance> {

    @Override
    protected String toName(Importance e) {
        return e.name().toLowerCase();
    }

    @Override
    protected Collection<Importance> elements() {
        return List.of(Importance.values());
    }

}
