package io.gobbler.commander.parser;

import java.util.Map;
import java.util.function.Predicate;

import static io.gobbler.commander.parser.Keywords.COMMANDS;

public class CommandListParser extends Parser {

    @Override
    public void handle(ObjectNode node, ParserContext context) {
        System.out.println("Tasks: " + node.get());

        node.<Map<String, ObjectNode>>get().forEach((name, definition) -> {
            System.out.println("task:" + definition.key());
            super.handle(definition, context);
        });
    }

    @Override
    public Predicate<ObjectNode> getPredicate() {
        return super.getPredicate()
                .and(value -> COMMANDS.match(value.key()));
    }

}
