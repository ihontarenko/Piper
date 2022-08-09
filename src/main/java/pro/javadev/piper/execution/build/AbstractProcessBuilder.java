package pro.javadev.piper.execution.build;

import pro.javadev.piper.common.Builder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.List.of;

public abstract class AbstractProcessBuilder implements Builder<Process> {

    private final List<String> commands = new ArrayList<>();

    protected AbstractProcessBuilder(String... commands) {
        withCommands(commands);
    }

    public AbstractProcessBuilder withCommands(String... commands) {
        this.commands.addAll(of(commands));
        return this;
    }

    @Override
    public Process build() {
        ProcessBuilder builder = new ProcessBuilder();
        Process        process;

        builder.command(commands.toArray(String[]::new));
        builder.redirectErrorStream(true);

        System.out.println(
                String.join(" ", commands)
        );

        try {
            process = builder.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return process;
    }

}
