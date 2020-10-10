package io.gobbler.commander.io;

public interface Dumper<F, T> {

    T dump(F source);

}
