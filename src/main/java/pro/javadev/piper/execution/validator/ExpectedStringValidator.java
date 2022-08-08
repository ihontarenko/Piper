package pro.javadev.piper.execution.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pro.javadev.piper.execution.Entry;
import pro.javadev.piper.execution.ExecutionContext;
import pro.javadev.piper.execution.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pro.javadev.piper.execution.ExecutionContext.State.READY_TO_DIE;

public class ExpectedStringValidator implements Validator {

    private static final Logger       LOGGER   = LoggerFactory.getLogger(ExpectedStringValidator.class);
    private final        List<String> expected = new ArrayList<>();

    public void addExpected(String expected) {
        this.expected.add(expected);
    }

    public void addExpected(List<String> expected) {
        this.expected.addAll(expected);
    }

    @Override
    public void validate(Entry entry, ExecutionContext context) {
        if (expected.stream().anyMatch(entry.line()::contains)) {
            context.toState(READY_TO_DIE);
            LOGGER.info("VALIDATOR FAILED");
        }
    }

    @Override
    public void configure(Map<String, Object> parameters) {
        addExpected(((List<String>) parameters.get("text")));
    }

}
