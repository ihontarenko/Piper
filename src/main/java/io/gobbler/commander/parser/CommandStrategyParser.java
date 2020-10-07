package io.gobbler.commander.parser;

import java.util.function.Predicate;

import static io.gobbler.commander.parser.Keywords.STRATEGY;

public class CommandStrategyParser extends Parser {

    @Override
    public void handle(ObjectNode node, ParserContext context) {
        System.out.println("CommandStrategy: " + node.get());
    }

    @Override
    public Predicate<ObjectNode> getPredicate() {
        return value -> STRATEGY.match(value.key());
    }

}
