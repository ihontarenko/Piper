package io.gobbler.commander.structure;

import java.util.HashMap;
import java.util.Map;

abstract public class BaseValues<V> {

    protected final Map<String, V> values;

    public BaseValues() {
        this.values = new HashMap<>();
    }

    public V get(String name) {
        return values.get(name);
    }

}
