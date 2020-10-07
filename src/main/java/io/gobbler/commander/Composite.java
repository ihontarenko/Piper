package io.gobbler.commander;

public interface Composite<C extends Composite<C>> {

    void add(C object);

    boolean has(C object);

    void remove(C object);

}
