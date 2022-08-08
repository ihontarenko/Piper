package pro.javadev.piper.converter.text;

import pro.javadev.piper.common.KeyValues;
import pro.javadev.piper.common.ansi.AnsiColorsKeyValues;
import pro.javadev.piper.common.Converter;
import pro.javadev.piper.converter.FlattenMapConverter;
import org.apache.commons.text.StringSubstitutor;

import static java.lang.String.format;

public class AnsiTextConverter implements Converter<String, String> {

    public static final KeyValues<Object> COLORS = new KeyValues<>(
            new FlattenMapConverter("ansi:", ".").convert(new AnsiColorsKeyValues())
    );

    @Override
    public String convert(String source) {
        return StringSubstitutor.replace(format("%s${ansi:RESET}", source), COLORS);
    }

}
