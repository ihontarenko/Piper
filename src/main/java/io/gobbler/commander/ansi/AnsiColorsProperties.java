package io.gobbler.commander.ansi;

import io.gobbler.commander.Properties;

import java.util.HashMap;

import static java.util.Arrays.stream;

@SuppressWarnings({"unsed"})
public class AnsiColorsProperties extends Properties<String> {

    public AnsiColorsProperties() {
        super(new HashMap<String, String>() {{
            stream(AnsiColors.values()).forEach(value
                    -> put(value.getName(), value.getValue()));
        }});
    }
}
