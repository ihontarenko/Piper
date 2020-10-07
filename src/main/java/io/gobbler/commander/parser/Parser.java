package io.gobbler.commander.parser;

import io.gobbler.commander.composite.AbstractComponent;

import java.util.Objects;
import java.util.function.Predicate;

import static java.util.Objects.nonNull;

abstract public class Parser
        extends AbstractComponent<ObjectNode, Parser, ParserContext> {

    public Predicate<ObjectNode> getPredicate() {
        return (value) -> nonNull(value) && nonNull(value.getValue());
    }

}
