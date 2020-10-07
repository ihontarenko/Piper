package io.gobbler.commander;

public interface Loader<I, O> {

    O load(I source) throws Exception;

}
