package io.gobbler.commander.builder;

import io.gobbler.commander.CommandLineBuilder;

import java.io.IOException;

abstract public class AbstractProcessBuilder<B extends AbstractProcessBuilder<B>> implements CommandLineBuilder<B, Process> {

    protected final ProcessBuilder builder = new ProcessBuilder();

    @Override
    public Process build() throws IOException {
        return builder.start();
    }

    @Override
    public B commands(String... commands) {
        builder.command(commands);

        return (B) this;
    }

    @Override
    public B predefined(String... commands) {
        return (B) this;
    }

}
