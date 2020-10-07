package io.gobbler.commons;

public interface KeyValue<K> {

    <V> V getValue(K key);

    <V> void setValue(K key, V value);

}
