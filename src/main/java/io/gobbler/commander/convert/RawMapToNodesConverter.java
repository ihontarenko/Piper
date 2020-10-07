package io.gobbler.commander.convert;

import io.gobbler.commander.node.MapNode;
import io.gobbler.commons.Converter;

import java.util.Map;

public class RawMapToNodesConverter implements Converter<Map<String, Object>, MapNode> {

    @Override
    public MapNode convert(Map<String, Object> source) {
        MapNode root = new MapNode("root");

        source.forEach((key, values)
                -> root.getValue().put(key, (values instanceof Map) ? convert((Map<String, Object>) values) : values));

        return root;
    }

}
