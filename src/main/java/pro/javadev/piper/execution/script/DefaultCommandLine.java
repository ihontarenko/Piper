package pro.javadev.piper.execution.script;

import pro.javadev.piper.ApplicationContext;

import static java.lang.String.format;

public class DefaultCommandLine implements CommandLine {

    private final String line;

    public DefaultCommandLine(String line) {
        this.line = line;
    }

    @Override
    public String getLine() {
        return line;
    }

    @Override
    public boolean isSubScript() {
        return line.startsWith(ApplicationContext.CONTEXT.getConfiguration().getSubScriptPrefix());
    }

    @Override
    public String toString() {
        return format("%sSCRIPT: [%s]", isSubScript() ? "SUB_" : "", line);
    }

}
