package io.gobbler.commander.parser;

import java.util.function.Predicate;

import static io.gobbler.commander.parser.Keywords.BINS;

public class BinsParser extends Parser {

    @Override
    public void handle(ObjectNode node, ParserContext context) {
        System.out.println("Bins: " + node.get());
    }

    @Override
    public Predicate<ObjectNode> getPredicate() {
        return super.getPredicate()
                .and(value -> BINS.match(value.key()));
    }

}
