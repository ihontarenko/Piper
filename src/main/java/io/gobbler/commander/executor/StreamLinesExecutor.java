package io.gobbler.commander.executor;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.function.Consumer;

public class StreamLinesExecutor implements Runnable {

    private final InputStream stream;
    private final Consumer<String> consumer;

    public StreamLinesExecutor(InputStream stream, Consumer<String> consumer) {
        this.stream = stream;
        this.consumer = consumer;
    }

    @Override
    public void run() {
        new BufferedReader(new InputStreamReader(stream)).lines()
                .forEach(consumer);
    }

}
