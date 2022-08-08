package pro.javadev.piper;

import static java.lang.String.format;

public final class Version {

    private String version;

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return format("VERSION[%s]", version);
    }
}
