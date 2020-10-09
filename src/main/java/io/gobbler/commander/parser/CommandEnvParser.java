package io.gobbler.commander.parser;

public class CommandEnvParser extends EnvParser {

    @Override
    public void handle(ObjectNode node, ParserContext context) {
        System.out.println("CommandEnvParser: -> ");
        super.handle(node, context);
    }

}
