package pro.javadev.piper.execution.build;

public class WindowsProcessBuilder extends AbstractProcessBuilder {

    public WindowsProcessBuilder() {
        super("cmd.exe", "/C");
    }

}
