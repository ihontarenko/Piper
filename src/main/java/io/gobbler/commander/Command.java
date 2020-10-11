package io.gobbler.commander;

import io.gobbler.commander.converter.text.AnsiTextConverter;
import org.apache.commons.cli.*;

@SuppressWarnings({"unsed"})
public enum Command {

    EXECUTE("execute", "exec", "run", "do") {
        @Override
        public Options getOptions() {
            Options options = new Options();

            options.addOption(new Option("f", "file", true, ANSI.convert("${ansi:GREEN_BOLD_BRIGHT}specify an alternate gobbler file")));
            options.addOption(new Option("v", "verbose", false, ANSI.convert("${ansi:GREEN_BOLD_BRIGHT}verbose mode")));
            options.addOption(new Option("q", "quite", false, ANSI.convert("${ansi:GREEN_BOLD_BRIGHT}quite mode")));

            return options;
        }
    },

    ABOUT("about") {
        @Override
        public Options getOptions() {
            Options options = new Options();

            options.addOption(new Option("a", "author", false, ANSI.convert("${ansi:GREEN_BOLD_BRIGHT}display author name")));
            options.addOption(new Option("v", "version", false, ANSI.convert("${ansi:GREEN_BOLD_BRIGHT}display version name")));
            options.addOption(new Option("f", "full", false, ANSI.convert("${ansi:GREEN_BOLD_BRIGHT}display full information")));

            return options;
        }
    };

    private final static AnsiTextConverter ANSI = new AnsiTextConverter();
    private final        String[]          names;

    Command(String... names) {
        this.names = names;
    }

    public static Command valueFor(String value) {
        Command command = ABOUT;

        for (Command current : values()) {
            for (String name : current.names) {
                if (name.equalsIgnoreCase(value)) {
                    command = current;
                    break;
                }
            }
        }

        return command;
    }

    public String getName() {
        return names[0];
    }

    public CommandLine getCommandLine(String... arguments) {
        try {
            return new DefaultParser().parse(getOptions(), arguments);
        } catch (ParseException exception) {
            throw new RuntimeException(exception);
        }
    }

    abstract public Options getOptions();

}
