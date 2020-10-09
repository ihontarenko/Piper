package io.gobbler.commander.common.node;

import java.util.HashMap;
import java.util.Map;

public class MapNode extends AbstractNode<String, Map<String, Object>> {

    public MapNode(String key, Map<String, Object> value) {
        super(key, value);
    }

    public MapNode(String key) {
        this(key, new HashMap<>());
    }

}
