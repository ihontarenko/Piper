package pro.javadev.piper;

import pro.javadev.piper.common.Holder;
import pro.javadev.piper.configurator.*;
import pro.javadev.piper.configurator.script.StepsConfigurator;
import pro.javadev.piper.configurator.script.ValidatorsConfigurator;
import pro.javadev.piper.converter.WrapIntoHolderConverter;
import pro.javadev.piper.common.Loader;
import pro.javadev.piper.io.loader.YamlLoader;
import pro.javadev.piper.io.parser.SnakeYAMLParser;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import static pro.javadev.piper.common.Holder.of;

public class ApplicationContextLoader implements Loader<String[], ApplicationContext> {


    @Override
    public ApplicationContext load(String[] source) throws FileNotFoundException {

        ApplicationContext context = ApplicationContext.CONTEXT;

        context.getArguments().addAll(List.of(source));

        Map<String, Object> values = new SnakeYAMLParser().parse(
                new YamlLoader().load(Constants.DEFAULT_CONFIGURATION_FILE_NAME)
        );

        context.getValues().putAll(values);

        Holder node = new WrapIntoHolderConverter().convert(values);

        RootConfigurator root = new RootConfigurator();

        root.add(new PiperConfigurator()
                .add(new VersionConfigurator())
                .add(new ScriptsConfigurator()
                        .add(new ScriptConfigurator()
                                .add(new ValidatorsConfigurator())
                                .add(new TextConfigurator())
                                .add(new KeyValuesConfigurator())
                                .add(new StepsConfigurator())
                        )
                )
                .add(new KeyValuesConfigurator())
        );

        root.handle(node, of(context));

        return context;
    }



}
