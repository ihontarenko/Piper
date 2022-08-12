package pro.javadev.piper.configurator;

public enum Keyword {

    // main keywords
    VERSION("version"),
    ENVIRONMENT("environment", "env", "properties"),
    SCRIPTS("scripts"),
    CONFIG("cfg", "config", "configuration"),
    EXTENDS("extends", "include", "based_on"),

    // script's keywords,
    STEPS("steps", "lines"),
    DESCRIPTION("description"),
    VALIDATORS("validators");

    private final String[] names;

    Keyword(String... names) {
        this.names = names;
    }

    public boolean match(String target) {
        boolean matched = false;

        for (String name : names) {
            if (target.equalsIgnoreCase(name)) {
                matched = true; break;
            }
        }

        return matched;
    }

    public static Keyword find(String name) {
        Keyword keyword = null;

        for (Keyword value : values()) {
            if (value.match(name)) {
                keyword = value;
                break;
            }
        }

        return keyword;
    }

}
