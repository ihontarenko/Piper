package io.gobbler.commander;

import io.gobbler.commander.command.CommandTaskRegistry;

public interface ApplicationContext {

    Properties<String> getEnvironment();

    Properties<String> getProperties();

    CommandTaskRegistry getRegistry();

}
