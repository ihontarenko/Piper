package io.gobbler.commons;

import java.util.HashMap;
import java.util.Map;

abstract public class KeyValueHashMap extends HashMap<String, Object> implements KeyValue<String> {

    public KeyValueHashMap() {
        super(1 << 5);
    }

    public KeyValueHashMap(int capacity) {
        super(capacity);
    }

    public KeyValueHashMap(Map<? extends String, ?> foreign) {
        super(foreign);
    }

    @Override
    public <V> V getValue(String key) {
        return (V) get(key);
    }

    @Override
    public <V> void setValue(String key, V value) {
        put(key, value);
    }

}
