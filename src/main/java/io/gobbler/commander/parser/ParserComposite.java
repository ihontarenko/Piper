package io.gobbler.commander.parser;

import io.gobbler.commander.Composite;
import io.gobbler.commander.Processor;

public interface ParserComposite<I, O, P extends Composite<P> & Processor<I, O>> extends Composite<P>, Processor<I, O> {



}
