package io.gobbler.commander.parser;

import io.gobbler.commander.common.Holder;

import java.util.Map;
import java.util.function.Predicate;

import static java.util.function.Predicate.not;

public class RootParser extends Parser {

    @Override
    public Predicate<ObjectNode> getPredicate() {
        return super.getPredicate().and(not(value -> value.get(Map.class).isEmpty()));
    }

    @Override
    public void handle(ObjectNode node, Holder value) {
        super.handle(node, value);
    }

}
