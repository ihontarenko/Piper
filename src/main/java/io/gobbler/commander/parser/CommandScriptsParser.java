package io.gobbler.commander.parser;

import java.util.function.Predicate;

import static io.gobbler.commander.parser.Keywords.SCRIPTS;

public class CommandScriptsParser extends Parser {

    @Override
    public void handle(ObjectNode node, ParserContext context) {
        System.out.println("CommandScriptsParser: " + node.get());
    }

    @Override
    public Predicate<ObjectNode> getPredicate() {
        return value -> SCRIPTS.match(value.key());
    }

}
