package io.gobbler.commander.proprties;

import io.gobbler.commander.PropertyAccessor;
import io.gobbler.commons.KeyValueHashMap;

import java.util.List;
import java.util.Map;

public class CommandProperties extends KeyValueHashMap {

    public static final String COMMAND_PATH_SCRIPTS = "scripts";

    private static final PropertyAccessor ACCESSOR = new PropertyAccessor();

    public CommandProperties(Map<? extends String, ?> foreign) {
        super(foreign);
    }

    public List<String> getScripts() {
        return ACCESSOR.read(this, COMMAND_PATH_SCRIPTS);
    }

}
