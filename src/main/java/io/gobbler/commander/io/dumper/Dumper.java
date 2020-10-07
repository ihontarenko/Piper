package io.gobbler.commander.io.dumper;

public interface Dumper<F, T> {

    T dump(F source);

}
