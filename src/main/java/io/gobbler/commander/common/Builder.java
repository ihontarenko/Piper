package io.gobbler.commander.common;

import java.io.IOException;

public interface Builder<V> {

    V build() throws IOException;

}
