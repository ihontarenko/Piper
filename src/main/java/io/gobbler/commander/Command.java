package io.gobbler.commander;

public enum Command {

    HELP("help"),
    EXECUTE("execute", "exec", "run", "do"),
    ABOUT("about");

    private final String[] names;

    Command(String... names) {
        this.names = names;
    }

    public String getName() {
        return names[0];
    }

    public static Command valueFor(String value) {
        Command command = ABOUT;

        for (Command current : values()) {
            for (String name : current.names) {
                if (name.equalsIgnoreCase(value)) {
                    command = current; break;
                }
            }
        }

        return command;
    }

}
