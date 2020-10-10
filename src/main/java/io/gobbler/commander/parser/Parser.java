package io.gobbler.commander.parser;

import io.gobbler.commander.common.Holder;
import io.gobbler.commander.common.composite.AbstractComponent;

import java.util.Map;
import java.util.function.Predicate;

import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;

abstract public class Parser
        extends AbstractComponent<ObjectNode, Parser, Holder> {

    @Override
    public void handle(ObjectNode node, Holder value) {
        requireNonNull(value, "VALUE CANNOT BE NULL");

        for (Map.Entry<String, ObjectNode> entry : node.<Map<String, ObjectNode>>get().entrySet()) {
            boolean undefined = true;

            for (Parser child : children) {
                if (child.getPredicate().test(entry.getValue())) {
                    child.handle(entry.getValue(), value);
                    undefined = false;
                    break;
                }
            }

            if (undefined) {
                throw new ParseException(this, entry.getKey());
            }
        }
    }

    public Predicate<ObjectNode> getPredicate() {
        return (value) -> nonNull(value) && nonNull(value.getValue());
    }

}
