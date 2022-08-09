package pro.javadev.piper.command;

import org.apache.commons.cli.CommandLine;
import pro.javadev.piper.ApplicationContext;
import pro.javadev.piper.common.ansi.AnsiColors;
import pro.javadev.piper.converter.text.AnsiTextConverter;

import java.util.function.Consumer;

import static java.lang.String.format;

public class InfoCommand implements Command {

    private static final AnsiTextConverter CONVERTER = new AnsiTextConverter();
    private static final Consumer<String>  CONSUMER  = System.out::println;

    @Override
    public void execute(CommandLine line) {
        ApplicationContext context = ApplicationContext.CONTEXT;

        context.getScripts().forEach((name, script) -> {
            println(format("${ansi:%s}%s (%s)",
                    AnsiColors.GREEN_BOLD_BRIGHT.name(), name, script.getDescription()));
            println(format("${ansi:%s}%s",
                    AnsiColors.YELLOW_BOLD_BRIGHT.name(), "-".repeat(32)));
        });
    }

    private void println(String message) {
        CONSUMER.accept(
                CONVERTER.convert(message)
        );
    }

}
