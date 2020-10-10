package io.gobbler.commander.parser;

import io.gobbler.commander.common.Holder;
import io.gobbler.commander.common.composite.AbstractComponent;

import java.util.Map;
import java.util.function.Predicate;

import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;

abstract public class Parser
        extends AbstractComponent<Holder, Parser, Holder> {

    @Override
    public void handle(Holder node, Holder holder) {
        requireNonNull(holder, "VALUE CANNOT BE NULL");

        for (Map.Entry<String, Holder> entry : node.<Map<String, Holder>>get().entrySet()) {
            boolean undefined = true;

            for (Parser child : children) {
                if (child.getPredicate().test(entry.getValue())) {
                    child.handle(entry.getValue(), holder);
                    undefined = false;
                    break;
                }
            }

            if (undefined) {
                throw new SyntaxErrorException(this, entry.getKey());
            }
        }
    }

    public Predicate<Holder> getPredicate() {
        return (value) -> nonNull(value) && nonNull(value.get());
    }

}
