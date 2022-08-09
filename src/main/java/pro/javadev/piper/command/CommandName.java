package pro.javadev.piper.command;

@SuppressWarnings({"unsed"})
public enum CommandName {

    EXECUTE("execute", "exec", "run"),
    HELP("help"),
    ABOUT("about"),
    INFO("info"),
    PRINT_ENV("env", "show-env");

    private final String[] names;

    CommandName(String... names) {
        this.names = names;
    }

    public static CommandName valueFor(String value) {
        CommandName command = ABOUT;

        for (CommandName current : values()) {
            for (String name : current.names) {
                if (name.equalsIgnoreCase(value)) {
                    command = current;
                    break;
                }
            }
        }

        return command;
    }

    public String getName() {
        return names[0];
    }

}
