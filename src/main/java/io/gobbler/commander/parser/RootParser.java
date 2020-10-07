package io.gobbler.commander.parser;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;

public class RootParser extends Parser<Map<String, Object>> {

    @Override
    public Predicate<Map<String, Object>> isApplicable(Map<String, Object> value) {
        return super.isApplicable(value).and(Predicate.not(Map::isEmpty));
    }

    @Override
    public void handle(Map<String, Object> value, ParserContext context) {

    }

    //    @Override
//    public RootNode process(Map<String, Object> source) {
//
//        source.forEach((name, value) -> {
//
//            for (var child : children) {
//                if (child.isApplicable(value)) {
//                    child.process(value);
//                }
//            }
//
//        });
//
//        return null;
//    }

//    @Override
//    public boolean isApplicable(Map<String, Object> source) {
//        throw new UnsupportedOperationException("ROOT PARSER DOESNT RESPONSIBLE FOR ANYONE BLOCK");
//    }

}
