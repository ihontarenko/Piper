package pro.javadev.piper.execution.build;

import java.util.Locale;

public class ProcessBuilderFactory {

    public static AbstractProcessBuilder getProcessBuilder() {
        AbstractProcessBuilder builder;

        if (System.getProperty("os.name").toLowerCase(Locale.ROOT).contains("windows")) {
            builder = new WindowsProcessBuilder();
        } else {
            builder = new LinuxProcessBuilder();
        }

        return builder;
    }

}
