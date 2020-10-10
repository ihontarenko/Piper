package io.gobbler.commander.converter;

import io.gobbler.commander.common.Converter;
import io.gobbler.commander.common.Holder;

import java.util.HashMap;
import java.util.Map;

import static io.gobbler.commander.common.Holder.of;

public class WrapHolderConverter implements Converter<Map<String, Object>, Holder> {

    @Override
    public Holder convert(Map<String, Object> source) {
        Holder root = of(null, "ROOT");

        wrap(source, root);

        return root;
    }

    private Holder wrap(Map<String, ?> input, Holder node) {
        Map<String, Holder> wrapper = new HashMap<>();

        for (Map.Entry<String, ?> entry : input.entrySet()) {
            Holder value = of(null, entry.getKey());

            if (entry.getValue() instanceof Map) {
                wrapper.put(entry.getKey(), wrap((Map<String, ?>) entry.getValue(), value));
            } else {
                value.set(entry.getValue());
                wrapper.put(entry.getKey(), value);
            }
        }

        node.set(wrapper);

        return node;
    }

}
