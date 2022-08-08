package pro.javadev.piper.execution.validator;

import pro.javadev.piper.common.ansi.AnsiColors;
import pro.javadev.piper.execution.ColoredPrinterConsumer;
import pro.javadev.piper.execution.ExecutionContext;
import pro.javadev.piper.execution.Validator;

import static java.lang.String.format;
import static pro.javadev.piper.execution.ExecutionContext.State.READY_TO_DIE;

public abstract class AbstractValidator implements Validator {

    protected final static ColoredPrinterConsumer PRINTER_CONSUMER
            = new ColoredPrinterConsumer(AnsiColors.WHITE_BOLD_BRIGHT, System.out::println) {{
                setBackground(AnsiColors.RED_BG_BRIGHT);
    }};

    protected void stopProcess(ExecutionContext context) {
        context.getProcess().destroyForcibly();
        context.toState(READY_TO_DIE);
        PRINTER_CONSUMER.accept(format("PRECESS STOPPED [%s]", getClass().getCanonicalName()));
    }

}
