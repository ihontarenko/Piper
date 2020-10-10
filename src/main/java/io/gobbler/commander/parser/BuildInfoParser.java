package io.gobbler.commander.parser;

import io.gobbler.commander.common.Holder;

import java.util.function.Predicate;

import static io.gobbler.commander.parser.Keywords.BUILD;

public class BuildInfoParser extends Parser {

    @Override
    public void handle(Holder node, Holder holder) {
        System.out.println("BuildInfo: " + node.get());
    }

    @Override
    public Predicate<Holder> getPredicate() {
        return value -> BUILD.match(value.inner().get());
    }

}
