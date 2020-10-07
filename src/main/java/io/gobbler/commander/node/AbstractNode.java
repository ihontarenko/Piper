package io.gobbler.commander.node;

import java.util.AbstractMap;

abstract public class AbstractNode<K, V> implements Node<K, V> {

    protected AbstractMap.SimpleEntry<K, V> entry;

    public AbstractNode(K key, V value) {
        this.entry = new AbstractMap.SimpleEntry<>(key, value);
    }

    public K getKey() {
        return entry.getKey();
    }

    public V getValue() {
        return entry.getValue();
    }

    public void setValue(V value) {
        entry.setValue(value);
    }

}
