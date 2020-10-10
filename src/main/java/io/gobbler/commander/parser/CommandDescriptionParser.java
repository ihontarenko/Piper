package io.gobbler.commander.parser;

import io.gobbler.commander.command.CommandTask;
import io.gobbler.commander.common.Holder;

import java.util.function.Predicate;

import static io.gobbler.commander.parser.Keywords.DESCRIPTION;

public class CommandDescriptionParser extends Parser {

    @Override
    public void handle(Holder node, Holder holder) {
        if (node.is(String.class) && holder.is(CommandTask.class)) {
            holder.<CommandTask>get().setDescription(node.get());
        }
    }

    @Override
    public Predicate<Holder> getPredicate() {
        return value -> DESCRIPTION.match(value.inner().get());
    }

}
