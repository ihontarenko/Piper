package io.gobbler.commander.proprties;

import io.gobbler.commander.PropertyAccessor;
import io.gobbler.commons.KeyValueHashMap;

import java.util.Map;

public class GlobalProperties extends KeyValueHashMap {

    public static final String PATH_COMMANDS = "gobbler.commands";
    public static final String PATH_ENVIRONMENTS = "gobbler.environments";
    public static final String PATH_VERSION = "version";
    public static final String PATH_BUILD_INFO = "gobbler.build";
    public static final String PATH_PROGRAMS = "gobbler.programs";

    private static final PropertyAccessor ACCESSOR = new PropertyAccessor();

    public GlobalProperties(Map<? extends String, ?> foreign) {
        super(foreign);

        ACCESSOR.write(this, PATH_BUILD_INFO, new BuildInfoProperties());
    }

    public BuildInfoProperties getBuildInfo() {
        return ACCESSOR.read(this, PATH_BUILD_INFO);
    }

    public Map<String, Map<String, ?>> getCommands() {
        return ACCESSOR.read(this, PATH_COMMANDS);
    }

    public String getVersion() {
        return ACCESSOR.read(this, PATH_VERSION);
    }
    
    public Map<String, String> getPrograms() {
        return ACCESSOR.read(this, PATH_PROGRAMS);
    }

    public Map<String, Map<String, ?>> getEnvironment() {
        return ACCESSOR.read(this, PATH_ENVIRONMENTS);
    }

}
