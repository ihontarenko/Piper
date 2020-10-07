package io.gobbler.commander.parser;

import java.util.function.Predicate;

import static io.gobbler.commander.parser.Keywords.BUILD;

public class BuildInfoParser extends Parser {

    @Override
    public void handle(ObjectNode node, ParserContext context) {
        System.out.println("BuildInfo: " + node.get());
    }

    @Override
    public Predicate<ObjectNode> getPredicate() {
        return value -> BUILD.match(value.key());
    }

}
