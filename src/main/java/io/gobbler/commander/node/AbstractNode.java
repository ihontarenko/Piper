package io.gobbler.commander.node;

import java.util.AbstractMap.SimpleEntry;
import java.util.Map;

@SuppressWarnings({"unused"})
abstract public class AbstractNode<K, V> extends SimpleEntry<K, V> implements Node<K, V> {

    public AbstractNode(K key, V value) {
        super(key, value);
    }

    public AbstractNode(Map.Entry<? extends K, ? extends V> entry) {
        this(entry.getKey(), entry.getValue());
    }

    @Override
    public K key() {
        return getKey();
    }

    @Override
    public <T> T get() {
        return (T) super.getValue();
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public <T> T get(Class<T> type) {
        return (T) super.getValue();
    }

    @Override
    public V set(V value) {
        return super.setValue(value);
    }

}
