package io.gobbler.commander.common.node;

import java.util.ArrayList;
import java.util.List;

public class ListNode extends AbstractNode<String, List<Object>> {

    public ListNode(String key, List<Object> value) {
        super(key, value);
    }

    public ListNode(String key) {
        this(key, new ArrayList<>());
    }

}
