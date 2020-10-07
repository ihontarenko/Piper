package io.gobbler.commander.parser;

import io.gobbler.commander.node.ValueNode;

import static java.lang.String.format;

public class ObjectNode extends ValueNode<String, Object> {

    private Class<?> type;

    public ObjectNode(String key, Object value) {
        super(key, value);
    }

    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return format("ObjectNode: {Type: %s, %s}", getType() == null ? "?" : getType().getSimpleName(), super.toString());
    }

}
