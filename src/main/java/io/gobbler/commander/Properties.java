package io.gobbler.commander;

import java.util.HashMap;
import java.util.Map;

public class Properties<V> extends HashMap<String, V> {

    @SafeVarargs
    public Properties(Map<? extends String, ? extends V>... values) {
        for (Map<? extends String, ? extends V> value : values) {
            putAll(value);
        }
    }

}
