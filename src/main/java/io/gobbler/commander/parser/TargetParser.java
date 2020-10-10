package io.gobbler.commander.parser;

import io.gobbler.commander.common.Holder;

import java.util.function.Predicate;

import static io.gobbler.commander.parser.Keywords.TARGET;

public class TargetParser extends Parser {

    @Override
    public void handle(Holder node, Holder holder) {
        System.out.println("TargetParser: " + node);
    }

    @Override
    public Predicate<Holder> getPredicate() {
        return super.getPredicate()
                .and(value -> TARGET.match(value.inner().get()));
    }

}
