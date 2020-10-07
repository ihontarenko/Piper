package io.gobbler.commander.parser;

import static java.lang.String.format;

public class ParseException extends Error {

    public ParseException(Parser<?, ?> parser, String name) {
        super(format("SYNTAX ERROR: PARSER '%s' CANNOT RECOGNIZE BLOCK '%s'", parser.getClass().getName(), name));
    }

}
