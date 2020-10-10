package io.gobbler.commander.parser;

import io.gobbler.commander.common.Holder;

public class CommandEnvParser extends EnvParser {

    @Override
    public void handle(ObjectNode node, Holder value) {
        System.out.println("CommandEnvParser: -> ");
        super.handle(node, value);
    }

}
