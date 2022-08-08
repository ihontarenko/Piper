package pro.javadev.piper.command;

import org.apache.commons.cli.*;
import pro.javadev.piper.converter.text.AnsiTextConverter;

import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;

import static pro.javadev.piper.command.CommandName.*;

public class CommandLineOptionPresets {

    private final static AnsiTextConverter ANSI           = new AnsiTextConverter();
    private static final Option            DEFAULT_OPTION = new Option("f", "file", true, "pass target config file '.piper.yaml'");

    public static final Map<CommandName, Supplier<Options>> OPTIONS = new EnumMap<>(CommandName.class) {{
        put(EXECUTE, () -> {
            Options options = new Options();

            options.addOption(DEFAULT_OPTION);
            options.addOption(new Option("v", "verbose", false, ANSI.convert("${ansi:GREEN_BOLD_BRIGHT}verbose mode")));
            options.addOption(new Option("q", "quite", false, ANSI.convert("${ansi:GREEN_BOLD_BRIGHT}quite mode")));

            return options;
        });
        put(ABOUT, () -> {
            Options options = new Options();

            options.addOption(DEFAULT_OPTION);
            options.addOption(new Option("a", "author", false, ANSI.convert("${ansi:GREEN_BOLD_BRIGHT}display author name")));
            options.addOption(new Option("v", "version", false, ANSI.convert("${ansi:GREEN_BOLD_BRIGHT}display version name")));
            options.addOption(new Option("f", "full", false, ANSI.convert("${ansi:GREEN_BOLD_BRIGHT}display full information")));

            return options;
        });
        put(PRINT_ENV, () -> {
            Options options = new Options();

            options.addOption(DEFAULT_OPTION);
            options.addOption(new Option("c", "colorize", false, ANSI.convert("${ansi:GREEN_BOLD_BRIGHT}colorize output")));

            return options;
        });
        put(HELP, Options::new);
    }};

    public static Options toOptions(CommandName command) {
        return OPTIONS.get(command).get();
    }

    public static CommandLine toCommandLine(CommandName command, String... arguments) {
        try {
            return new DefaultParser().parse(toOptions(command), arguments);
        } catch (ParseException exception) {
            throw new RuntimeException(exception);
        }
    }

}
