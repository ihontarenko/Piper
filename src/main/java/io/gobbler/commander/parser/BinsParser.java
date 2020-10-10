package io.gobbler.commander.parser;

import io.gobbler.commander.common.Holder;

import java.util.function.Predicate;

import static io.gobbler.commander.parser.Keywords.BINS;

public class BinsParser extends Parser {

    @Override
    public void handle(ObjectNode node, Holder value) {
        System.out.println("Bins: " + node.get());
    }

    @Override
    public Predicate<ObjectNode> getPredicate() {
        return super.getPredicate()
                .and(value -> BINS.match(value.key()));
    }

}
