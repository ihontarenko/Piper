package io.gobbler.commander.converter.text;

import io.gobbler.commander.Properties;
import io.gobbler.commander.ansi.AnsiColorsProperties;
import io.gobbler.commander.common.Converter;
import io.gobbler.commander.converter.FlattenMapConverter;
import org.apache.commons.text.StringSubstitutor;

import static java.lang.String.format;

public class AnsiTextConverter implements Converter<String, String> {

    public static final Properties<Object> COLORS = new Properties<>(
            new FlattenMapConverter("ansi:", ".").convert(new AnsiColorsProperties())
    );

    @Override
    public String convert(String source) {
        return StringSubstitutor.replace(format("%s${ansi:RESET}", source), COLORS);
    }

}
