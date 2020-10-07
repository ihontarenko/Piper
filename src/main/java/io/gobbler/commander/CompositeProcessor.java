package io.gobbler.commander;

import io.gobbler.commander.ast.AstNode;

public interface CompositeProcessor<I, O> extends
        Composite<CompositeProcessor<Object, ?>>, Processor<I, O> {
}
