package pro.javadev.piper.configurator;

import static java.lang.String.format;

public class TypeMismatchException extends Error {

    public TypeMismatchException(BaseConfigurator parser, Class<?> expected, Class<?> actual) {
        super(format("PARSER '%s': '%s'", parser.getClass().getName(),
                format("EXPECTED VALUE TYPE IS '%s' BUT PASSED '%s'", expected, actual)));
    }

}
