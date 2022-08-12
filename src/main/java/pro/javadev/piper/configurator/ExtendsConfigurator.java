package pro.javadev.piper.configurator;

import pro.javadev.piper.ApplicationContext;
import pro.javadev.piper.PiperException;
import pro.javadev.piper.common.DeepMerge;
import pro.javadev.piper.common.Holder;
import pro.javadev.piper.common.ansi.AnsiColors;
import pro.javadev.piper.converter.WrapIntoHolderConverter;
import pro.javadev.piper.execution.ColoredPrinterConsumer;
import pro.javadev.piper.io.loader.YamlLoader;
import pro.javadev.piper.io.parser.SnakeYAMLParser;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static pro.javadev.piper.configurator.Keyword.VERSION;

public class ExtendsConfigurator extends BaseConfigurator {

    protected final static ColoredPrinterConsumer PRINTER
            = new ColoredPrinterConsumer(AnsiColors.PURPLE_BOLD_BRIGHT, System.out::println);

    @Override
    public void handle(Holder node, Holder target) {
        validateHolder(node, List.class);
        validateHolder(target, ApplicationContext.class);

        ApplicationContext context = target.get();
        RootConfigurator   root    = context.getPiperConfigurator().get();

        for (Holder holder : node.<List<Holder>>get()) {
            String              piper   = holder.get();
            Map<String, Object> values  = loadPiperFile(piper);
            Holder              wrapped = new WrapIntoHolderConverter().convert(values);

            new DeepMerge().apply(values, context.getValues());

            PRINTER.accept(String.format("LOAD ADDITIONAL PIPER FILE: %s", piper));

            root.handle(wrapped, target);
        }
    }

    @Override
    public Predicate<Holder> applicable() {
        return super.applicable().and(value -> VERSION.match(value.inner().get()));
    }

    private Map<String, Object> loadPiperFile(String piperFile) {
        Map<String, Object> values;

        try {
            values = new SnakeYAMLParser().parse(new YamlLoader().load(piperFile));
        } catch (FileNotFoundException e) {
            throw new PiperException(e.getMessage());
        }

        return values;
    }

}
