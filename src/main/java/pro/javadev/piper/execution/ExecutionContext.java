package pro.javadev.piper.execution;

import pro.javadev.piper.ApplicationContext;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static pro.javadev.piper.execution.ExecutionContext.State.ALIVE;
import static pro.javadev.piper.execution.ExecutionContext.State.INFANT;

public interface ExecutionContext {

    List<Validator> getValidators();

    InputStream getInputStream();

    Process getProcess();

    Consumer<Entry> getConsumer();

    ApplicationContext getApplicationContext();

    void toState(State state);

    boolean isState(State state);

    enum State {
        INFANT, ALIVE, JUMP_OVER, READY_TO_DIE
    }

    class Context implements ExecutionContext {

        private final Supplier<Process>  supplier;
        private final ApplicationContext context;
        private final List<Validator>    validators = new ArrayList<>();
        private final Consumer<Entry>    consumer;
        private       Process            process;
        private       State              state;

        public Context(Supplier<Process> supplier, Consumer<Entry> consumer, ApplicationContext context) {
            this.supplier = supplier;
            this.consumer = consumer;
            this.context = context;
            this.state = INFANT;
        }

        @Override
        public List<Validator> getValidators() {
            return this.validators;
        }

        @Override
        public InputStream getInputStream() {
            return getProcess().getInputStream();
        }

        @Override
        public Process getProcess() {
            if (isState(INFANT)) {
                toState(ALIVE);
                this.process = this.supplier.get();
            }

            return this.process;
        }

        @Override
        public Consumer<Entry> getConsumer() {
            return this.consumer;
        }

        @Override
        public ApplicationContext getApplicationContext() {
            return this.context;
        }

        @Override
        public boolean isState(State state) {
            return this.state == state;
        }

        @Override
        public void toState(State state) {
            this.state = state;
        }

    }

}
