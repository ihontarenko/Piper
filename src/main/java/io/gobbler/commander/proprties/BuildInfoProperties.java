package io.gobbler.commander.proprties;

import io.gobbler.commander.PropertyAccessor;
import io.gobbler.commons.KeyValueHashMap;

public class BuildInfoProperties extends KeyValueHashMap {

    private static final PropertyAccessor ACCESSOR = new PropertyAccessor();

    public static final String PATH_TIMESTAMP = "timestamp";
    public static final String PATH_OS_NAME = "os";
    public static final String PATH_AUTHOR_NAME = "author";

    public String getTimestamp() {
        return getValue(PATH_TIMESTAMP);
    }

    public String getOs() {
        return getValue(PATH_OS_NAME);
    }

    public String getAuthor() {
        return getValue(PATH_AUTHOR_NAME);
    }

    public void setOsName(String name) {
        ACCESSOR.write(this, PATH_OS_NAME, name);
    }

    public void setAuthor(String name) {
        ACCESSOR.write(this, PATH_AUTHOR_NAME, name);
    }

    public void setTimestamp(String timestamp) {
        ACCESSOR.write(this, PATH_TIMESTAMP, timestamp);
    }

}
