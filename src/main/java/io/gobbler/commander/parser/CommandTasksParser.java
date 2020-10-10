package io.gobbler.commander.parser;

import io.gobbler.commander.command.CommandTask;
import io.gobbler.commander.common.Holder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static io.gobbler.commander.common.Holder.of;
import static io.gobbler.commander.parser.Keywords.COMMANDS;

public class CommandTasksParser extends Parser {

    @Override
    public void handle(Holder node, Holder holder) {
        List<CommandTask> tasks = new ArrayList<>();

        node.<Map<String, Holder>>get().forEach((name, definition) -> {
            CommandTask task = new CommandTask(name);
            super.handle(definition, of(task));
            tasks.add(task);
        });

        System.out.println("tasks -> ");
        System.out.println(tasks);
        System.out.println(holder);
    }

    @Override
    public Predicate<Holder> getPredicate() {
        return super.getPredicate()
                .and(value -> COMMANDS.match(value.inner().get()));
    }

}
