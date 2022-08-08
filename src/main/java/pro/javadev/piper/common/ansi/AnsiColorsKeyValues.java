package pro.javadev.piper.common.ansi;

import pro.javadev.piper.common.KeyValues;

import java.util.HashMap;

import static java.util.Arrays.stream;

@SuppressWarnings({"unsed"})
public class AnsiColorsKeyValues extends KeyValues<String> {

    public AnsiColorsKeyValues() {
        super(new HashMap<>() {{
            stream(AnsiColors.values()).forEach(value
                    -> put(value.getName(), value.getValue()));
        }});
    }
}
