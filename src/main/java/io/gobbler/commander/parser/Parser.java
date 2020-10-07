package io.gobbler.commander.parser;

import io.gobbler.commander.composite.AbstractComponent;

import java.util.Map;
import java.util.function.Predicate;

import static java.util.Objects.nonNull;

abstract public class Parser
        extends AbstractComponent<ObjectNode, Parser, ParserContext> {

    @Override
    public void handle(ObjectNode node, ParserContext context) {
        for (Map.Entry<String, ObjectNode> entry : node.<Map<String, ObjectNode>>get().entrySet()) {
            boolean undefined = true;

            for (Parser child : children) {
                if (child.getPredicate().test(entry.getValue())) {
                    child.handle(entry.getValue(), context);
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
