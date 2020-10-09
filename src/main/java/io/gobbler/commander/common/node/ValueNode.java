package io.gobbler.commander.common.node;

public class ValueNode<K, V> extends AbstractNode<K, V> {

    public ValueNode(K key, V value) {
        super(key, value);
    }

}
