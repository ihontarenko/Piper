package io.gobbler.commander;

import io.gobbler.commander.common.Builder;

public interface CommandLineBuilder<B extends CommandLineBuilder<B, V>, V> extends Builder<V> {

    B commands(String... commands);

    B predefined(String... commands);

}
