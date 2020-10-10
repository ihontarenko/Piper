package io.gobbler.commander.parser;

import io.gobbler.commander.common.Holder;

import java.util.Map;
import java.util.function.Predicate;

import static java.util.function.Predicate.not;

public class RootParser extends Parser {

    @Override
    public Predicate<Holder> getPredicate() {
        return super.getPredicate().and(not(value -> value.<Map>get().isEmpty()));
    }

    @Override
    public void handle(Holder node, Holder holder) {
        super.handle(node, holder);
    }

}
