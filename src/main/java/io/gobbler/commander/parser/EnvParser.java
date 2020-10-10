package io.gobbler.commander.parser;

import io.gobbler.commander.Properties;
import io.gobbler.commander.common.Holder;

import java.util.Map;
import java.util.function.Predicate;

import static io.gobbler.commander.parser.Keywords.ENVIRONMENT;
import static java.lang.String.format;

public class EnvParser extends Parser {

    @Override
    public void handle(Holder node, Holder holder) {
        if (holder.is(Properties.class)) {
            node.<Map<String, Holder>>get().forEach((key, value)
                    -> holder.<Properties<String>>get().put(key, value.get()));
        } else {
            throw new ParserException(this, format("HOLDER MUST CONTAIN VALUE OF TYPE '%s' BUT HAS '%s'", Properties.class, holder.type()));
        }
    }

    @Override
    public Predicate<Holder> getPredicate() {
        return super.getPredicate()
                .and(value -> ENVIRONMENT.match(value.inner().get()));
    }

}
