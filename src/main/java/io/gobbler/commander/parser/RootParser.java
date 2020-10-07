package io.gobbler.commander.parser;

import java.util.Map;
import java.util.function.Predicate;

import static java.util.function.Predicate.not;

public class RootParser extends Parser {

    @Override
    public Predicate<ObjectNode> getPredicate() {
        return super.getPredicate().and(not(value -> value.get(Map.class).isEmpty()));
    }

    @Override
    public void handle(ObjectNode value, ParserContext context) {
        for (Map.Entry<String, ObjectNode> entry : value.<Map<String, ObjectNode>>get().entrySet()) {
            boolean undefined = true;

            for (Parser child : children) {
                ObjectNode node = new ObjectNode(entry.getKey(), entry.getValue());

                if (child.getPredicate().test(node)) {
                    System.out.println("root-parser found: " + child.getClass());
                    child.handle(entry.getValue(), context);
                    undefined = false;
                    break;
                }
            }

            if (undefined) {
                throw new ParseException(this, entry.getKey());
            }
        }
    }

}
