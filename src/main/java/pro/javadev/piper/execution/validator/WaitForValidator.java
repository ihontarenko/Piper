package pro.javadev.piper.execution.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pro.javadev.piper.common.Countdown;
import pro.javadev.piper.execution.Entry;
import pro.javadev.piper.execution.ExecutionContext;
import pro.javadev.piper.execution.Validator;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static pro.javadev.piper.execution.ExecutionContext.State.READY_TO_DIE;

public class WaitForValidator implements Validator {

    private static final Logger    LOGGER = LoggerFactory.getLogger(WaitForValidator.class);
    private              Countdown countdown;
    private              TimeUnit  unit;
    private              long      duration;

    @Override
    public void configure(Map<String, Object> parameters) {
        setDuration(Long.valueOf((Integer) parameters.get("time")));
        setUnit(TimeUnit.valueOf((String) parameters.get("unit")));
    }

    @Override
    public void validate(Entry entry, ExecutionContext context) {
        ensureCountdown();

        Process process = context.getProcess();
        boolean spent   = countdown.spent(duration, unit);

        if (spent) {
            process.destroyForcibly();
            context.toState(READY_TO_DIE);
            countdown = null;
            LOGGER.info("VALIDATOR FAILED");
        }

    }

    private void ensureCountdown() {
        if (countdown == null) {
            countdown = new Countdown();
            countdown.start();
        }
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public TimeUnit getUnit() {
        return unit;
    }

    public void setUnit(TimeUnit unit) {
        this.unit = unit;
    }

}
