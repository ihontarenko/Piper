package io.gobbler.commander.structure;

import static java.lang.Float.valueOf;
import static java.lang.String.format;

public class Version {

    protected Float version;

    public Version(Float version) {
        this.version = version;
    }

    public Version(String version) {
        this(valueOf(version));
    }

    public void setVersion(Float version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return format("Version{version=%s}", version);
    }

}
