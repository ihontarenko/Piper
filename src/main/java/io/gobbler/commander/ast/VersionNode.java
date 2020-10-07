package io.gobbler.commander.ast;

import static java.lang.String.format;

public class VersionNode extends Node{

    private final String version;

    public VersionNode(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return format("Version{version='%s'}", version);
    }
}
