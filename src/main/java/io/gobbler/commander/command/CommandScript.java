package io.gobbler.commander.command;

import static io.gobbler.commander.Constants.SUB_COMMAND_LINE_PREFIX;
import static java.lang.String.format;

public class CommandScript {

    private final String line;

    public CommandScript(String line) {
        this.line = line;
    }

    public String getLine() {
        return line;
    }

    public boolean isSubCommand() {
        return line.startsWith(SUB_COMMAND_LINE_PREFIX);
    }

    @Override
    public String toString() {
        return format("SCRIPT: [%s]", line);
    }
}
