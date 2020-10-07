package io.gobbler.commander.node;

import java.util.AbstractMap.SimpleEntry;

public class KVNode<K, V> extends AbstractNode<K, V> {

    public KVNode(K key, V value) {
        super(key, value);
    }

}
