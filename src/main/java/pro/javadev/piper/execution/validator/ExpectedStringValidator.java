package pro.javadev.piper.execution.validator;

import pro.javadev.piper.execution.Entry;
import pro.javadev.piper.execution.ExecutionContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pro.javadev.piper.execution.ExecutionContext.State.READY_TO_DIE;

public class ExpectedStringValidator extends AbstractValidator {

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
            stopProcess(context);
        }
    }

    @Override
    public void configure(Map<String, Object> parameters) {
        addExpected(((List<String>) parameters.get("text")));
    }

    @Override
    public String toString() {
        return String.format("[STOP WORD: '%s']", expected);
    }

}
