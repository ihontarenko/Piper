package pro.javadev.piper.execution.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pro.javadev.piper.execution.Entry;
import pro.javadev.piper.execution.ExecutionContext;
import pro.javadev.piper.execution.Validator;

import java.util.Map;

import static pro.javadev.piper.execution.ExecutionContext.State.READY_TO_DIE;

public class LineCounterValidator implements Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(LineCounterValidator.class);
    private              int    threshold;

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public void validate(Entry entry, ExecutionContext context) {
        if (threshold < entry.ordinal()) {
            context.toState(READY_TO_DIE);
            LOGGER.info("VALIDATOR FAILED");
        }
    }

    @Override
    public void configure(Map<String, Object> parameters) {
        setThreshold((Integer) parameters.get("threshold"));
    }

}
