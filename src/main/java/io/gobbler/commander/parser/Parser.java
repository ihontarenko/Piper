package io.gobbler.commander.parser;

import io.gobbler.commander.composite.AbstractComponent;

import java.util.Objects;
import java.util.function.Predicate;

abstract public class Parser<S> extends AbstractComponent<S, ParserContext> {

    public Predicate<S> isApplicable(S value) {
        return Objects::nonNull;
    }

}
