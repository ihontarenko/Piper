package pro.javadev.piper.execution.build;

public class LinuxProcessBuilder extends AbstractProcessBuilder {

    public LinuxProcessBuilder() {
        super("/bin/bash", "-c");
    }

}
