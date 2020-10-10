package io.gobbler.commander.parser;

import io.gobbler.commander.common.Holder;

import java.util.function.Predicate;

import static io.gobbler.commander.parser.Keywords.VERSION;

public class VersionParser extends Parser {

    @Override
    public void handle(ObjectNode node, Holder value) {
        System.out.println("Version: " + node.get(String.class));
    }

    @Override
    public Predicate<ObjectNode> getPredicate() {
        return super.getPredicate()
                .and(value -> VERSION.match(value.key()));
    }

}
