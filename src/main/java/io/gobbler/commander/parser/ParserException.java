package io.gobbler.commander.parser;

import static java.lang.String.format;

public class ParserException extends Error {

    public ParserException(Parser parser, String message) {
        super(format("PARSER '%s': '%s'", parser.getClass().getName(), message));
    }

}
