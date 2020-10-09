package io.gobbler.commander.converter;

import io.gobbler.commander.parser.ObjectNode;
import io.gobbler.commander.Converter;

import java.util.HashMap;
import java.util.Map;

public class MapToObjectNodeConverter implements Converter<Map<String, Object>, ObjectNode> {

    @Override
    public ObjectNode convert(Map<String, Object> source) {
        ObjectNode root = new ObjectNode("ROOT", null);

        wrap(source, root);
        root.setType(source.getClass());

        return root;
    }

    private ObjectNode wrap(Map<String, ?> input, ObjectNode node) {
        Map<String, ObjectNode> wrapper = new HashMap<>();

        for (Map.Entry<String, ?> entry : input.entrySet()) {
            ObjectNode value = new ObjectNode(entry.getKey(), null);

            if (entry.getValue() instanceof Map) {
                wrapper.put(entry.getKey(), wrap((Map<String, ?>) entry.getValue(), value));
            } else {
                value.setValue(entry.getValue());
                wrapper.put(entry.getKey(), value);
            }

            if (entry.getValue() != null) {
                value.setType(entry.getValue().getClass());
            }
        }

        node.set(wrapper);

        return node;
    }

}
