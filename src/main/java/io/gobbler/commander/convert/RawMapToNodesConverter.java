package io.gobbler.commander.convert;

import io.gobbler.commander.parser.ObjectNode;
import io.gobbler.commons.Converter;

import java.util.List;
import java.util.Map;

public class RawMapToNodesConverter implements Converter<Map<String, Object>, ObjectNode> {

    @Override
    public ObjectNode convert(Map<String, Object> source) {
        ObjectNode root = new ObjectNode("root", null);

        for (Map.Entry<String, Object> entry : source.entrySet()) {
            System.out.println("key: " + entry.getKey());

            if (entry.getValue() instanceof Map) {
                System.out.println(entry.getValue());
                convert((Map<String, Object>) entry.getValue());
            } else if (entry.getValue() instanceof List) {

            } else {
                System.out.println("type: " + (entry.getValue() == null ? "NULLLL" : entry.getValue().getClass()) );
            }
        }

//        source.forEach((key, values)
//                -> root.getValue().put(key, (values instanceof Map) ? convert((Map<String, Object>) values) : values));

        return root;
    }

}
