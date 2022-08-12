package pro.javadev.piper;

import org.apache.commons.cli.CommandLine;
import pro.javadev.piper.command.CommandName;
import pro.javadev.piper.common.Holder;
import pro.javadev.piper.common.Registry;
import pro.javadev.piper.execution.script.Script;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ApplicationContext {

    ApplicationContext CONTEXT = new Context();

    List<String> getArguments();

    Version getVersion();

    GlobalConfig getConfiguration();

    Registry<String, Script> getScripts();

    Map<String, Object> getValues();

    CommandName getCurrentCommandName();

    CommandLine getParsedLine();

    Map<String, Holder> getProperties();

    Holder getPiperConfigurator();

    final class Context implements ApplicationContext {

        final Registry<String, Script> scripts       = new Registry<>();
        final GlobalConfig             configuration = new GlobalConfig.Configuration();
        final Version                  version       = new Version();
        final List<String>             arguments     = new ArrayList<>();
        final Map<String, Object>      values        = new HashMap<>();
        final Holder                   configurator  = Holder.of(null);
        final Map<String, Holder>      properties    = new HashMap<>() {{
            put(Constants.CURRENT_COMMAND_NAME, Holder.of(null));
            put(Constants.PARSED_LINE, Holder.of(null));
        }};

        @Override
        public CommandName getCurrentCommandName() {
            return getProperties().get(Constants.CURRENT_COMMAND_NAME).get();
        }

        @Override
        public CommandLine getParsedLine() {
            return getProperties().get(Constants.PARSED_LINE).get();
        }

        @Override
        public Map<String, Holder> getProperties() {
            return properties;
        }

        @Override
        public List<String> getArguments() {
            return arguments;
        }

        @Override
        public Version getVersion() {
            return version;
        }

        @Override
        public GlobalConfig getConfiguration() {
            return configuration;
        }

        @Override
        public Registry<String, Script> getScripts() {
            return scripts;
        }

        @Override
        public Map<String, Object> getValues() {
            return values;
        }

        @Override
        public Holder getPiperConfigurator() {
            return configurator;
        }
    }

}
