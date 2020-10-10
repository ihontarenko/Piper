package io.gobbler.commander.parser;

import io.gobbler.commander.common.Holder;

import java.util.function.Predicate;

import static io.gobbler.commander.parser.Keywords.VERSION;

public class VersionParser extends Parser {

    @Override
    public void handle(Holder node, Holder holder) {
        System.out.println("Version: " + node.<String>get());
    }

    @Override
    public Predicate<Holder> getPredicate() {
        return super.getPredicate()
                .and(value -> VERSION.match(value.inner().get()));
    }

}
