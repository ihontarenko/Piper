package pro.javadev.piper;

import org.apache.commons.cli.*;
import pro.javadev.piper.command.CommandLineOptionPresets;
import pro.javadev.piper.command.CommandName;
import pro.javadev.piper.common.Holder;
import pro.javadev.piper.common.Loader;
import pro.javadev.piper.configurator.*;
import pro.javadev.piper.configurator.script.StepsConfigurator;
import pro.javadev.piper.configurator.script.ValidatorsConfigurator;
import pro.javadev.piper.converter.WrapIntoHolderConverter;
import pro.javadev.piper.io.loader.YamlLoader;
import pro.javadev.piper.io.parser.SnakeYAMLParser;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import static pro.javadev.piper.common.Holder.of;

public class ApplicationContextLoader implements Loader<String[], ApplicationContext> {


    @Override
    public ApplicationContext load(String[] arguments) throws FileNotFoundException, ParseException {
        ApplicationContext  context    = ApplicationContext.CONTEXT;
        CommandLineParser   parser     = new DefaultParser();
        CommandName         command    = arguments != null && arguments.length > 0 ? CommandName.valueFor(arguments[0]) : CommandName.HELP;
        Options             options    = CommandLineOptionPresets.toOptions(command);
        CommandLine         line       = parser.parse(options, arguments, false);
        String              piperFile  = line.hasOption('f') ? line.getOptionValue('f') : Constants.DEFAULT_CONFIGURATION_FILE_NAME;
        Map<String, Holder> properties = context.getProperties();

        properties.put(Constants.CURRENT_COMMAND_NAME, Holder.of(command));
        properties.put(Constants.PARSED_LINE, Holder.of(line));
        properties.put(Constants.COMMAND_LINE_OPTIONS, Holder.of(options));
        properties.put(Constants.PIPER_FILE, Holder.of(piperFile));

        assert arguments != null;

        context.getArguments().addAll(List.of(arguments));

        Map<String, Object> values = new SnakeYAMLParser().parse(
                new YamlLoader().load(piperFile)
        );

        context.getValues().putAll(values);

        Holder           node = new WrapIntoHolderConverter().convert(values);
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
                .add(new ExtendsConfigurator())
                .add(new KeyValuesConfigurator())
        );

        context.getPiperConfigurator().set(root);

        root.handle(node, of(context));

        return context;
    }


}
