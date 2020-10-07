package io.gobbler.commander.node;

public class ValueNode<K, V> extends AbstractNode<K, V> {

    public ValueNode(K key, V value) {
        super(key, value);
    }

}
