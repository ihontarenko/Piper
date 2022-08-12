package pro.javadev.piper.common;

import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;

public class DeepMerge implements BiFunction<Map<String, Object>, Map<String, Object>, Map<String, Object>> {

    @Override
    public Map<String, Object> apply(Map<String, Object> v1, Map<String, Object> v2) {
        return deepMerge(v1, v2);
    }

    @SuppressWarnings({"unchecked"})
    private Map<String, Object> deepMerge(Map<String, Object> v1, Map<String, Object> v2) {

        v1.forEach((k, v) -> {
            if (v instanceof Map && v2.get(k) instanceof Map) {
                v2.put(k, deepMerge((Map<String, Object>) v, (Map<String, Object>) v2.get(k)));
            } else if (v instanceof List && v2.get(k) instanceof List) {
                v2.put(k, deepMerge((List<Object>) v, (List<Object>) v2.get(k)));
            } else {
                v2.put(k, v);
            }
        });

        return v2;
    }

    @SuppressWarnings({"unchecked"})
    private List<Object> deepMerge(List<Object> v1, List<Object> v2) {
        int c = 0;

        for (Object v : v1) {
            if (v instanceof Map && v2.get(c) instanceof Map) {
                v2.add(deepMerge((Map<String, Object>) v, (Map<String, Object>) v2.get(c)));
            } else if (v instanceof List && v2.get(c) instanceof List) {
                v2.add(deepMerge((List<Object>) v, (List<Object>) v2.get(c)));
            } else {
                v2.add(v);
            }
            c++;
        }

        return v2;
    }

}
