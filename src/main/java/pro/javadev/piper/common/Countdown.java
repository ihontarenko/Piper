package pro.javadev.piper.common;

import java.util.concurrent.TimeUnit;

public class Countdown {

    private long start = 0L;

    public void start() {
        start = System.nanoTime();
    }

    public void stop() {
        start = 0L;
    }

    public void reset() {
        stop();
        start();
    }

    public boolean spent(long duration, TimeUnit unit) {
        return (System.nanoTime() > (start + unit.toNanos(duration)));
    }

}
