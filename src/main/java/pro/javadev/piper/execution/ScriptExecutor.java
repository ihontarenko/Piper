package pro.javadev.piper.execution;

import pro.javadev.piper.ApplicationContext;
import pro.javadev.piper.common.Builder;
import pro.javadev.piper.common.Registry;
import pro.javadev.piper.common.ansi.AnsiColors;
import pro.javadev.piper.execution.build.ProcessBuilderFactory;
import pro.javadev.piper.execution.script.CommandLine;
import pro.javadev.piper.execution.script.Script;

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
            throw new RuntimeException(format("UNABLE TO RUN UNDEFINED SCRIPT NAME %s", scriptName));
        }

        printer.setColor(AnsiColors.BLACK_BOLD_BRIGHT);
        printer.setBackground(AnsiColors.YELLOW_BOLD_BRIGHT);
        printer.accept("");
        printer.accept(format(" START SCRIPT: [ %s ] ",
                Optional.ofNullable(script.getDescription()).orElseGet(script::getName)));
        printer.accept("");

        for (CommandLine line : script.getLines()) {
            if (line.isSubScript()) {
                String subScript = line.getLine().substring(1);

                printer.setColor(AnsiColors.BLUE_BOLD_BRIGHT);
                printer.accept(" SUB-SCRIPT: ");
                printer.accept(format("\t[ %s ]", subScript));
                printer.accept("");

                process(scripts, subScript);
            } else {
                String           converted = new CommandLineCompleter(context, script).convert(line.getLine());
                Builder<Process> builder   = ProcessBuilderFactory.getProcessBuilder().withCommands(converted);
                ExecutionContext execution = new ExecutionContext.Context(builder::build, new PrintEntryConsumer(), context);

                printer.setColor(AnsiColors.GREEN_BOLD_BRIGHT);
                printer.accept(" EXECUTE LINE: ");
                printer.accept(format("\t[ %s ]", converted));
                printer.accept("");

                execution.getValidators().addAll(script.getValidators());

                new CommandLineExecutor(execution).execute();
            }
        }

    }

}
