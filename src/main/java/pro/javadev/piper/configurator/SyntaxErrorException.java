package pro.javadev.piper.configurator;

import static java.lang.String.format;

public class SyntaxErrorException extends Error {

    public SyntaxErrorException(BaseConfigurator configurator, String name) {
        super(format("SYNTAX ERROR: '%s' CANNOT RECOGNIZE BLOCK '%s'", configurator.getClass().getName(), name));
    }

}
