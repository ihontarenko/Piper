package io.gobbler.commander.parser;

import io.gobbler.commander.ast.AstNode;
import io.gobbler.commander.ast.VersionNode;

public class VersionParser extends Parser<String, VersionNode> {

    @Override
    public VersionNode process(String source) {
        return new VersionNode(source);
    }

    @Override
    public Class<?> getType() {
        return String.class;
    }
}
