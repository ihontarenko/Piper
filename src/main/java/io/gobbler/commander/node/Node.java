package io.gobbler.commander.node;

public interface Node<K, V> {

    K getKey();

    V getValue();

    void setValue(V value);

}
