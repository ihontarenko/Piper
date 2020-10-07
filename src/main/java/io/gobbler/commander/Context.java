package io.gobbler.commander;

import io.gobbler.commander.proprties.GlobalProperties;
import io.gobbler.commons.KeyValueHashMap;

import java.util.Map;

import static java.lang.System.getenv;

public class Context extends KeyValueHashMap {

    public static final String SYSTEM_CONFIG_KEY = "configuration";
    public static final String SYSTEM_ENVIRONMENT_KEY = "env";
    public static final int INITIAL_CAPACITY = 1 << 5; // 256

    public Context() {
        super(INITIAL_CAPACITY);

        put(SYSTEM_ENVIRONMENT_KEY, getenv());
    }

    public GlobalProperties getGlobal() {
        return getValue(SYSTEM_CONFIG_KEY);
    }

    public void setRawConfiguration(Map<String, Object> values) {
        setValue(SYSTEM_CONFIG_KEY, new GlobalProperties(values));
    }

    public Map<String, String> getSystemEnvironment() {
        return getValue(SYSTEM_ENVIRONMENT_KEY);
    }

}
