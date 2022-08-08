package pro.javadev.piper.command;

import org.apache.commons.cli.CommandLine;
import pro.javadev.piper.converter.text.AnsiTextConverter;

import java.util.function.Consumer;

import static java.lang.String.format;
import static pro.javadev.piper.common.ansi.AnsiColors.*;

public class AboutCommand implements Command {

    private static final AnsiTextConverter CONVERTER = new AnsiTextConverter();
    private static final Consumer<String>  CONSUMER  = System.out::println;

    @Override
    public void execute(CommandLine line) {
        printLN(format("${ansi:%s}%s", GREEN_BOLD_BRIGHT.name(), "+".repeat(32)));
        printLN(format("${ansi:%s}PIPER - RUN DESCRIBED COMMANDS", BLUE_BOLD_BRIGHT.name()));
        printLN(format("${ansi:%s}IVAN HONTARENKO (2022)", YELLOW_BOLD_BRIGHT.name()));
        printLN(format("${ansi:%s}%s", GREEN_BOLD_BRIGHT.name(), "+".repeat(32)));
    }

    private void printLN(String message) {
        CONSUMER.accept(CONVERTER.convert(message));
    }

}
