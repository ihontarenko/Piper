package io.gobbler.commander.parser;

import io.gobbler.commander.command.CommandScript;
import io.gobbler.commander.command.CommandTask;
import io.gobbler.commander.common.Holder;

import java.util.List;
import java.util.function.Predicate;

import static io.gobbler.commander.parser.Keywords.SCRIPTS;

public class CommandScriptsParser extends Parser {

    @Override
    public void handle(Holder node, Holder holder) {
        if (node.is(List.class) && holder.is(CommandTask.class)) {
            for (String line : node.<List<String>>get()) {
                holder.<CommandTask>get().addScript(new CommandScript(line));
            }
        }
    }

    @Override
    public Predicate<Holder> getPredicate() {
        return value -> SCRIPTS.match(value.inner().get());
    }

}
