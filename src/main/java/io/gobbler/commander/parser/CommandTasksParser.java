package io.gobbler.commander.parser;

import io.gobbler.commander.command.CommandTask;
import io.gobbler.commander.common.Holder;

import java.util.Map;
import java.util.function.Predicate;

import static io.gobbler.commander.parser.Keywords.COMMANDS;

public class CommandTasksParser extends Parser {

    @Override
    public void handle(ObjectNode node, Holder value) {
        node.<Map<String, ObjectNode>>get().forEach((name, definition)
                -> super.handle(definition, Holder.of(new CommandTask(name))));
    }

    @Override
    public Predicate<ObjectNode> getPredicate() {
        return super.getPredicate()
                .and(value -> COMMANDS.match(value.key()));
    }

}
