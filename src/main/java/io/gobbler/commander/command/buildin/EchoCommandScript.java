package io.gobbler.commander.command.buildin;

import io.gobbler.commander.command.CommandScript;

public class EchoCommandScript extends CommandScript {

    public EchoCommandScript(String string) {
        super("echo -e " + string);
    }

}
