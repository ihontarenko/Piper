package io.gobbler.commander.ansi;

public enum AnsiColors {

    RESET("\u001B[0m"),
    // REGULAR COLORS
    BLACK("\u001B[0;30m"),   
    RED("\u001B[0;31m"), 
    GREEN("\u001B[0;32m"),   
    YELLOW("\u001B[0;33m"), 
    BLUE("\u001B[0;34m"),
    PURPLE("\u001B[0;35m"),
    CYAN("\u001B[0;36m"),
    WHITE("\u001B[0;37m"),
    // BOLD
    BLACK_BOLD("\u001B[1;30m"), 
    RED_BOLD("\u001B[1;31m"),
    GREEN_BOLD("\u001B[1;32m"), 
    YELLOW_BOLD("\u001B[1;33m"), 
    BLUE_BOLD("\u001B[1;34m"),   
    PURPLE_BOLD("\u001B[1;35m"), 
    CYAN_BOLD("\u001B[1;36m"),   
    WHITE_BOLD("\u001B[1;37m"),
    // UNDERLINE
    BLACK_UNDERLINED("\u001B[4;30m"), 
    RED_UNDERLINED("\u001B[4;31m"),
    GREEN_UNDERLINED("\u001B[4;32m"), 
    YELLOW_UNDERLINED("\u001B[4;33m"), 
    BLUE_UNDERLINED("\u001B[4;34m"),   
    PURPLE_UNDERLINED("\u001B[4;35m"), 
    CYAN_UNDERLINED("\u001B[4;36m"),   
    WHITE_UNDERLINED("\u001B[4;37m"),
    // BG
    BLACK_BG("\u001B[40m"), 
    RED_BG("\u001B[41m"),
    GREEN_BG("\u001B[42m"), 
    YELLOW_BG("\u001B[43m"), 
    BLUE_BG("\u001B[44m"),   
    PURPLE_BG("\u001B[45m"), 
    CYAN_BG("\u001B[46m"),   
    WHITE_BG("\u001B[47m"),
    // HIGH INTENSITY
    BLACK_BRIGHT("\u001B[0;90m"),
    RED_BRIGHT("\u001B[0;91m"),
    GREEN_BRIGHT("\u001B[0;92m"),
    YELLOW_BRIGHT("\u001B[0;93m"),
    BLUE_BRIGHT("\u001B[0;94m"),
    PURPLE_BRIGHT("\u001B[0;95m"),
    CYAN_BRIGHT("\u001B[0;96m"),
    WHITE_BRIGHT("\u001B[0;97m"),
    // BOLD HIGH INTENSITY
    BLACK_BOLD_BRIGHT("\u001B[1;90m"),
    RED_BOLD_BRIGHT("\u001B[1;91m"),
    GREEN_BOLD_BRIGHT("\u001B[1;92m"),
    YELLOW_BOLD_BRIGHT("\u001B[1;93m"),
    BLUE_BOLD_BRIGHT("\u001B[1;94m"),
    PURPLE_BOLD_BRIGHT("\u001B[1;95m"),
    CYAN_BOLD_BRIGHT("\u001B[1;96m"),
    WHITE_BOLD_BRIGHT("\u001B[1;97m"),
    // HIGH INTENSITY BGS
    BLACK_BG_BRIGHT("\u001B[0;100m"),
    RED_BG_BRIGHT("\u001B[0;101m"),
    GREEN_BG_BRIGHT("\u001B[0;102m"),
    YELLOW_BG_BRIGHT("\u001B[0;103m"),
    BLUE_BG_BRIGHT("\u001B[0;104m"),
    PURPLE_BG_BRIGHT("\u001B[0;105m"),
    CYAN_BG_BRIGHT("\u001B[0;106m"),
    WHITE_BG_BRIGHT("\u001B[0;107m");

    private final String value;

    AnsiColors(String color) {
        this.value = color;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name();
    }

    @Override
    public String toString() {
        return value;
    }

}
