package pro.javadev.piper.command;

import org.apache.commons.cli.CommandLine;
import pro.javadev.piper.Constants;
import pro.javadev.piper.converter.text.AnsiTextConverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Consumer;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;
import static pro.javadev.piper.common.ansi.AnsiColors.GREEN_BOLD_BRIGHT;

public class AboutCommand implements Command {

    private static final AnsiTextConverter CONVERTER = new AnsiTextConverter();
    private static final Consumer<String>  CONSUMER  = System.out::println;

    @Override
    public void execute(CommandLine commandLine) {
        String line;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                requireNonNull(getClass().getResourceAsStream(Constants.BANNER_FILE), "UNABLE LOAD BANNER FILE")))) {
            while ((line = reader.readLine()) != null) {
                CONSUMER.accept(CONVERTER.convert(line));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
