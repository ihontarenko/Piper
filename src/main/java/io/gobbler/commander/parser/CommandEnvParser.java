package io.gobbler.commander.parser;

import io.gobbler.commander.Properties;
import io.gobbler.commander.command.CommandTask;
import io.gobbler.commander.common.Holder;

import java.util.Map;

import static io.gobbler.commander.common.Holder.of;

public class CommandEnvParser extends EnvParser {

    @Override
    public void handle(Holder node, Holder holder) {
        if (node.is(Map.class) && holder.is(CommandTask.class)) {
            Properties<String> environment = new Properties<>();
            super.handle(node, of(environment));
            holder.<CommandTask>get().setEnvironment(environment);
        }
    }

}
