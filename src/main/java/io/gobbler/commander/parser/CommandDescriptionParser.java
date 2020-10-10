package io.gobbler.commander.parser;

import io.gobbler.commander.common.Holder;

import java.util.function.Predicate;

import static io.gobbler.commander.parser.Keywords.DESCRIPTION;

public class CommandDescriptionParser extends Parser {

    @Override
    public void handle(ObjectNode node, Holder value) {
        System.out.println("CommandDescriptionParser: " + node.get());
    }

    @Override
    public Predicate<ObjectNode> getPredicate() {
        return value -> DESCRIPTION.match(value.key());
    }

}
