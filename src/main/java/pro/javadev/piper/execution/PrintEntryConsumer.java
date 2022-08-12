package pro.javadev.piper.execution;

import java.util.function.Consumer;

import static java.lang.String.format;

public class PrintEntryConsumer implements Consumer<Entry> {

    private static final Consumer<String> CONSUMER = System.out::println;

    @Override
    public void accept(Entry entry) {
        CONSUMER.accept(format("[#%06d] %s", entry.ordinal(), entry.line().trim()));
    }

}
