package io.gobbler.commander;

public interface CommandLineBuilder<B extends CommandLineBuilder<B, V>, V> extends Builder<V> {

    B commands(String... commands);

    B predefined(String... commands);

}
