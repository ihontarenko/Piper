package pro.javadev.piper.configurator;

import static java.lang.String.format;

public class UndefinedException extends Error {

    public UndefinedException(BaseConfigurator configurator, String name) {
        super(format("CONFIGURATOR [%s] CANNOT FIND KEYWORD FOR NAME: [%s]",
                configurator.getClass().getName(), name));
    }

    public UndefinedException(BaseConfigurator configurator, Keyword keyword) {
        super(format("CONFIGURATOR [%s] CANNOT FIND TYPE FOR KEYWORD: [%s]",
                configurator.getClass().getName(), keyword));
    }

    public UndefinedException(BaseConfigurator configurator, Class<?> type, Keyword keyword) {
        super(format("CONFIGURATOR [%s] CANNOT FIND CONFIGURATOR FOR TYPE AND KEYWORD: [%s] [%s]",
                configurator.getClass().getName(), type.getName(), keyword));
    }

}
