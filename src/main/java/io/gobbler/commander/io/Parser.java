package io.gobbler.commander.io;

public interface Parser<S, R> {

    R parse(S stream);

}
