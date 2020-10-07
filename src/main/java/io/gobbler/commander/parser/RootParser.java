package io.gobbler.commander.parser;

import io.gobbler.commander.ast.AstNode;
import io.gobbler.commander.ast.RootNode;

import java.util.Map;

public class RootParser extends Parser<Map<String, Object>, RootNode, Parser<Object, AstNode, ?>> {

    @Override
    public RootNode process(Map<String, Object> source) {

        source.forEach((name, value) -> {

            for (var child : children) {
                if (child.isApplicable(value)) {
                    child.process(value);
                }
            }

        });

        return null;
    }

    @Override
    public boolean isApplicable(Map<String, Object> source) {
        throw new UnsupportedOperationException("ROOT PARSER DOESNT RESPONSIBLE FOR ANYONE BLOCK");
    }

}
