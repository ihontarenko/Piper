package io.gobbler.commander.parser;

import java.util.Map;
import java.util.function.Predicate;

import static io.gobbler.commander.parser.Keywords.ENVIRONMENT;

public class EnvParser extends Parser {

    @Override
    public void handle(ObjectNode node, ParserContext context) {
        System.out.println("Environment: ");

        node.<Map<String, ObjectNode>>get().forEach((s, s2) -> {
            System.out.println("\t"+s+"->"+s2.get(String.class));
        });
    }

    @Override
    public Predicate<ObjectNode> getPredicate() {
        return super.getPredicate()
                .and(value -> ENVIRONMENT.match(value.key()));
    }

}
