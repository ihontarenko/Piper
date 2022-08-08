package pro.javadev.piper.execution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.function.Consumer;

import static pro.javadev.piper.execution.ExecutionContext.State.ALIVE;
import static pro.javadev.piper.execution.ExecutionContext.State.READY_TO_DIE;

public class CommandLineExecutor implements Executor {

    private final ExecutionContext context;

    public CommandLineExecutor(ExecutionContext context) {
        this.context = context;
    }

    @Override
    public void execute() {
        BufferedReader      reader   = new BufferedReader(new InputStreamReader(context.getInputStream()));
        Consumer<Entry>     consumer = context.getConsumer();
        Optional<Validator> wrapped  = context.getValidators().stream().reduce(Validator::chain);
        int                 counter  = 0;
        String              line;

        try {
            while ((line = reader.readLine()) != null) {
                Entry entry = new LineEntry(line, counter++);

                wrapped.ifPresent(validator -> validator.validate(entry, context));

                if (context.isState(ALIVE)) {
                    consumer.accept(entry);
                } else if (context.isState(READY_TO_DIE)) {
                    break;
                }
            }
        } catch (IOException ignore) {

        }
    }

}
