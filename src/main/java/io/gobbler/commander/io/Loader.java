package io.gobbler.commander.io;

public interface Loader<I, O> {

    O load(I source) throws Exception;

}
