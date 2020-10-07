package io.gobbler.commander.parser;

import io.gobbler.commander.node.AbstractNode;

public class ValueNode<K, V> extends AbstractNode<K, V> {

    public ValueNode(K key, V value) {
        super(key, value);
    }

}
