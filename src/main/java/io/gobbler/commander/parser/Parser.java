package io.gobbler.commander.parser;

import io.gobbler.commander.Composite;
import io.gobbler.commander.Processor;
import io.gobbler.commander.ast.AstNode;

import java.util.List;

abstract public class Parser<S, R>
        implements Composite<Parser>, Processor<S, R>
{

    protected List<Parser> children;

    @Override
    public void add(P parser) {
        children.add(parser);
    }

    @Override
    public boolean has(P parser) {
        return false;
    }

    @Override
    public void remove(P parser) {

    }

    @Override
    public Class<?> getType() {
        return Object.class;
    }

}
