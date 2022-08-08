package pro.javadev.piper.common;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class KeyValues<V> extends HashMap<String, V> {

    @SafeVarargs
    public KeyValues(Map<? extends String, ? extends V>... values) {
        Stream.of(values).forEach(this::putAll);
    }

}
