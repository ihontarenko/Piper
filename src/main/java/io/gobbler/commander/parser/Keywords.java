package io.gobbler.commander.parser;

public enum Keywords {

    // root keywords
    VERSION("version"),
    BUILD("build", "buildInfo"),
    ENVIRONMENT("environment", "env", "properties"),
    BINS("bin", "programs"),
    COMMANDS("commands", "tasks"),
    // commands keywords
    SCRIPTS("scripts"),
    DESCRIPTION("description"),
    STRATEGY("strategy");

    private final String[] names;

    Keywords(String... names) {
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

}
