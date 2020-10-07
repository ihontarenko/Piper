package io.gobbler.commander.io.parser;

public interface Parser<S, R> {

    R parse(S stream);

}
