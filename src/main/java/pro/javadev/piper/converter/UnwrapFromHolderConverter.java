package pro.javadev.piper.converter;

import pro.javadev.piper.common.Converter;
import pro.javadev.piper.common.Holder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnwrapFromHolderConverter implements Converter<Map<String, Holder>, Map<String, Object>> {

    @Override
    public Map<String, Object> convert(Map<String, Holder> input) {
        return unwrap(input);
    }

    public Map<String, Object> unwrap(Map<String, Holder> input) {
        Map<String, Object> result = new HashMap<>();

        input.forEach((key, holder) -> {
            if (holder.is(Map.class)) {
                result.put(key, unwrap(holder.<Map<String, Holder>>get()));
            } else if (holder.is(List.class)) {
                result.put(key, unwrap(holder.<List<Holder>>get()));
            } else {
                result.put(key, holder.get());
            }
        });

        return result;
    }

    public List<Object> unwrap(List<Holder> input) {
        List<Object> objects = new ArrayList<>();

        for (Holder item : input) {
            if (item.is(Map.class)) {
                objects.add(unwrap(item.<Map<String, Holder>>get()));
            } else {
                objects.add(item.get());
            }
        }

        return objects;
    }

}
