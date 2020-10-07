package io.gobbler.commander.convert;

import io.gobbler.commander.parser.ObjectNode;
import io.gobbler.commons.Converter;

import java.util.Collections;
import java.util.Map;

import static java.util.Map.of;

public class MapToObjectNodeConverter implements Converter<Map<String, Object>, ObjectNode> {

    @Override
    public ObjectNode convert(Map<String, Object> source) {
        ObjectNode root = new ObjectNode("root", null);

        wrap(of("root", source), root);

        return root;
    }

    private void wrap(Map<String, ?> input, ObjectNode node) {
        for (Map.Entry<String, ?> entry : input.entrySet()) {
            if (entry.getValue() instanceof Map) {
                wrap((Map<String, ?>) entry.getValue(), new ObjectNode(entry.getKey(), null));
            } else {
                node.set(entry.getValue());
            }
        }
    }

}
