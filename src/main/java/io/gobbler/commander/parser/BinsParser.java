package io.gobbler.commander.parser;

import io.gobbler.commander.common.Holder;

import java.util.function.Predicate;

import static io.gobbler.commander.parser.Keywords.BINS;

public class BinsParser extends Parser {

    @Override
    public void handle(Holder node, Holder holder) {
        System.out.println("Bins: " + node.get());
    }

    @Override
    public Predicate<Holder> getPredicate() {
        return super.getPredicate()
                .and(value -> BINS.match(value.inner().get()));
    }

}
