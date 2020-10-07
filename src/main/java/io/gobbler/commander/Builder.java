package io.gobbler.commander;

import java.io.IOException;

public interface Builder<V> {

    V build() throws IOException;

}
