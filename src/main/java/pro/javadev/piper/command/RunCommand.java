package pro.javadev.piper.command;

import org.apache.commons.cli.CommandLine;
import pro.javadev.piper.ApplicationContext;
import pro.javadev.piper.converter.text.AnsiTextConverter;
import pro.javadev.piper.execution.ScriptExecutor;

import java.util.List;
import java.util.function.Consumer;

public class RunCommand implements Command {

    private static final AnsiTextConverter CONVERTER = new AnsiTextConverter();
    private static final Consumer<String>  CONSUMER  = System.out::println;

    @Override
    public void execute(CommandLine line) {
        ApplicationContext context   = ApplicationContext.CONTEXT;
        List<String>       arguments = line.getArgList();

        arguments.remove(0);

        new ScriptExecutor(context, arguments.get(0)).execute();
    }

}
