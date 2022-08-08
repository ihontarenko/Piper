package pro.javadev.piper.execution.validator;

import pro.javadev.piper.common.Countdown;
import pro.javadev.piper.execution.Entry;
import pro.javadev.piper.execution.ExecutionContext;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class WaitForValidator extends AbstractValidator {

    private Countdown countdown;
    private TimeUnit  unit;
    private long      duration;

    @Override
    public void configure(Map<String, Object> parameters) {
        setDuration(Long.valueOf((Integer) parameters.get("time")));
        setUnit(TimeUnit.valueOf((String) parameters.get("unit")));
    }

    @Override
    public void validate(Entry entry, ExecutionContext context) {
        ensureCountdown();

        boolean spent = countdown.spent(duration, unit);

        if (spent) {
            stopProcess(context);
            countdown = null;
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
