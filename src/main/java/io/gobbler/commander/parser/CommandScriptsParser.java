package io.gobbler.commander.parser;

import io.gobbler.commander.Properties;
import io.gobbler.commander.common.Holder;

import java.util.function.Predicate;

import static io.gobbler.commander.parser.Keywords.SCRIPTS;

public class CommandScriptsParser extends Parser {

    @Override
    public void handle(ObjectNode node, Holder value) {
        System.out.println("CommandScriptsParser: " + value);
        System.out.println("CommandScriptsParser is Properties: " + value.is(Properties.class));
    }

    @Override
    public Predicate<ObjectNode> getPredicate() {
        return value -> SCRIPTS.match(value.key());
    }

}
