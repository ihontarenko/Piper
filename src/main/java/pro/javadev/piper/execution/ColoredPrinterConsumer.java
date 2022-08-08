package pro.javadev.piper.execution;

import pro.javadev.piper.common.ansi.AnsiColors;
import pro.javadev.piper.converter.text.AnsiTextConverter;

import java.util.function.Consumer;

import static java.lang.String.format;

public class ColoredPrinterConsumer implements Consumer<String> {

    private static final AnsiTextConverter CONVERTER = new AnsiTextConverter();
    private final        Consumer<String>  consumer;
    private              AnsiColors        color;
    private              AnsiColors        background = AnsiColors.BLACK_BG_BRIGHT;

    public ColoredPrinterConsumer(AnsiColors color, Consumer<String> consumer) {
        this.color = color;
        this.consumer = consumer;
    }

    public AnsiColors getColor() {
        return color;
    }

    public void setColor(AnsiColors color) {
        this.color = color;
    }

    public AnsiColors getBackground() {
        return background;
    }

    public void setBackground(AnsiColors background) {
        this.background = background;
    }

    @Override
    public void accept(String message) {
        consumer.accept(CONVERTER.convert(format("${ansi:%s}${ansi:%s}%s",
                color.name(), background.name(), message)));
    }

}
