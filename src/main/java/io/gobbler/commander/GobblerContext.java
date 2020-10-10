package io.gobbler.commander;

import io.gobbler.commander.command.CommandTaskRegistry;

import java.util.HashMap;

public class GobblerContext implements ApplicationContext {

    protected Properties<String>  environment;
    protected Properties<String>  properties;
    protected CommandTaskRegistry registry;

    public GobblerContext() {
        this.properties = new Properties<>(new HashMap<>());
        this.registry = new CommandTaskRegistry();
        this.environment = new Properties<>(System.getenv());
    }

    @Override
    public Properties<String> getEnvironment() {
        return environment;
    }

    @Override
    public Properties<String> getProperties() {
        return properties;
    }

    @Override
    public CommandTaskRegistry getRegistry() {
        return registry;
    }

}
