package io.gobbler.commander;

public interface Reader<V, T> {

    V read(T target);

}
