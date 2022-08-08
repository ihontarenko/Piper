package pro.javadev.piper.command;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import pro.javadev.piper.converter.text.AnsiTextConverter;

import static java.lang.String.format;

public class HelpCommand implements Command {

    @Override
    public void execute(CommandLine line) {
        AnsiTextConverter converter = new AnsiTextConverter();
        HelpFormatter     formatter = new HelpFormatter();

        formatter.setArgName("ARGUMENT");
        formatter.setSyntaxPrefix(converter.convert("${ansi:BLUE_BOLD_BRIGHT}COMMAND USAGE:${ansi:RESET} "));
        formatter.setWidth(128);
        formatter.setLongOptPrefix(" --");
        formatter.setLongOptSeparator("=");
        formatter.setOptPrefix(" -");

        String separator = converter.convert(format("${ansi:RED_BOLD_BRIGHT}%s", "-".repeat(32)));

        for (CommandName value : CommandName.values()) {
            formatter.printHelp(converter.convert(format("${ansi:YELLOW_BOLD_BRIGHT}%s", value.getName())),
                    separator, CommandLineOptionPresets.toOptions(value), separator);
        }
    }

}
