package io.gobbler.commander.parser;

import java.util.function.Predicate;

public class VersionParser extends Parser {

    @Override
    public void handle(ObjectNode value, ParserContext context) {
        System.out.println("VersionParser: " + value);
    }

    @Override
    public Predicate<ObjectNode> getPredicate() {
        return super.getPredicate()
                .and(value -> value.get(String.class).equalsIgnoreCase("version"));
    }

}
