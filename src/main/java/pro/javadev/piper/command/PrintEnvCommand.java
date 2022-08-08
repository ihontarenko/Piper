package pro.javadev.piper.command;

import org.apache.commons.cli.CommandLine;
import pro.javadev.piper.converter.text.AnsiTextConverter;

import java.util.function.Consumer;

import static java.lang.String.format;
import static pro.javadev.piper.common.ansi.AnsiColors.GREEN_BOLD_BRIGHT;
import static pro.javadev.piper.common.ansi.AnsiColors.RED_BOLD_BRIGHT;

public class PrintEnvCommand implements Command {

    private static final AnsiTextConverter CONVERTER = new AnsiTextConverter();
    private static final Consumer<String>  CONSUMER  = System.out::println;

    @Override
    public void execute(CommandLine line) {
        System.getenv().forEach((key, value) -> {
            if (line.hasOption('c')) {
                CONSUMER.accept(
                        CONVERTER.convert(
                                format("${ansi:%s}%s${ansi:RESET}=${ansi:%s}%s", RED_BOLD_BRIGHT.name(), key, GREEN_BOLD_BRIGHT.name(), value)
                        )
                );
            } else {
                CONSUMER.accept(format("%s=%s", key, value));
            }
        });
    }

}
