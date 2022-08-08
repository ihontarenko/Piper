package pro.javadev.piper;

import pro.javadev.piper.common.Registry;
import pro.javadev.piper.script.Script;

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

    final class Context implements ApplicationContext {

        final Registry<String, Script> scripts       = new Registry<>();
        final GlobalConfig             configuration = new GlobalConfig.Configuration();
        final Version                  version       = new Version();
        final List<String>             arguments     = new ArrayList<>();
        final Map<String, Object>      values        = new HashMap<>();

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

    }

}
