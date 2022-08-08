package pro.javadev.piper.command;

import org.apache.commons.cli.CommandLine;

public interface Command {
    void execute(CommandLine line);
}
