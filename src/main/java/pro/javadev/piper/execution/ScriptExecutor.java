package pro.javadev.piper.execution;

import pro.javadev.piper.ApplicationContext;
import pro.javadev.piper.common.Builder;
import pro.javadev.piper.common.Registry;
import pro.javadev.piper.common.ansi.AnsiColors;
import pro.javadev.piper.execution.build.ProcessBuilderFactory;
import pro.javadev.piper.script.CommandLine;
import pro.javadev.piper.script.Script;

import java.util.Optional;

import static java.lang.String.format;

public class ScriptExecutor implements Executor {

    private final ApplicationContext context;
    private final String             name;

    public ScriptExecutor(ApplicationContext context, String name) {
        this.context = context;
        this.name = name;
    }

    @Override
    public void execute() {
        process(context.getScripts(), name);
    }

    private void process(Registry<String, Script> scripts, String scriptName) {
        Script                 script  = scripts.get(scriptName);
        ColoredPrinterConsumer printer = new ColoredPrinterConsumer(AnsiColors.YELLOW_BOLD_BRIGHT, System.out::println);

        if (script == null) {
            throw new RuntimeException(String.format("UNABLE TO RUN UNDEFINED SCRIPT NAME %s", scriptName));
        }

        printer.accept(format("START SCRIPT: %s", Optional.ofNullable(script.getDescription()).orElseGet(script::getName)));

        for (CommandLine line : script.getLines()) {
            if (line.isSubScript()) {
                String subScriptName = line.getLine().substring(1);

                printer.setColor(AnsiColors.BLUE_BOLD_BRIGHT);
                printer.accept(format("INTO SUB-SCRIPT: %s", subScriptName));

                process(scripts, subScriptName);
            } else {
                String           converted = new CommandLineCompleter(context, script).convert(line.getLine());
                Builder<Process> builder   = ProcessBuilderFactory.getProcessBuilder().withCommands(converted);
                ExecutionContext execution = new ExecutionContext.Context(builder::build, new PrintEntryConsumer(), context);

                printer.setColor(AnsiColors.GREEN_BOLD_BRIGHT);
                printer.accept(format("EXECUTE LINE: %s", converted));

                execution.getValidators().addAll(script.getValidators());

                new CommandLineExecutor(execution).execute();
            }
        }

    }

}
