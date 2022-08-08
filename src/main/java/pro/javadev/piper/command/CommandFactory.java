package pro.javadev.piper.command;

import java.util.Objects;

public class CommandFactory {

    public static Command getCommand(CommandName name) {
        Command command = null;

        switch (name) {
            case ABOUT:
                command = new AboutCommand();
                break;
            case EXECUTE:
                command = new RunCommand();
                break;
            case PRINT_ENV:
                command = new PrintEnvCommand();
                break;
            case HELP:
                command = new HelpCommand();
                break;
        }

        Objects.requireNonNull(command, "UNABLE TO FIND COMMAND");

        return command;
    }

}
