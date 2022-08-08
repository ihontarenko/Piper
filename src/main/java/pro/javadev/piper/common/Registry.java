package pro.javadev.piper.common;

import java.util.HashMap;

public class Registry<K, V extends Registry.KeyAware<K>> extends HashMap<K, V> {

    public void register(V value) {
        put(value.getKey(), value);
    }

    public void unregister(K key) {
        remove(key);
    }

    public void unregister(V value) {
        unregister(value.getKey());
    }

    public boolean has(K key) {
        return containsKey(key);
    }

    public boolean has(V value) {
        return has(value.getKey());
    }

    public interface KeyAware<K> {
        K getKey();
    }

}
