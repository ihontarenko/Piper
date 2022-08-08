package pro.javadev.piper.converter;

import pro.javadev.piper.common.Converter;
import pro.javadev.piper.common.Holder;

import java.util.*;

import static java.lang.String.format;
import static pro.javadev.piper.common.Holder.of;

public class WrapIntoHolderConverter implements Converter<Map<String, Object>, Holder> {

    @Override
    public Holder convert(Map<String, Object> source) {
        return wrap("ROOT", source);
    }

    private Holder wrap(String name, Object value) {
        Holder holder = of(null, name);

        if (value instanceof Map) {
            Map<String, Holder> wrapped = new HashMap<>();

            ((Map<String, ?>)value).forEach((k, v)
                    -> wrapped.put(k, wrap(k, v)));

            holder.set(wrapped);
        } else if (value instanceof List) {
            List<Holder> wrapped = new ArrayList<>();
            final int[] counter = new int[]{0};

            ((List<?>)value).forEach((v) -> wrapped.add(
                    wrap(format("index_%s", counter[0]++), v)
            ));

            holder.set(wrapped);
        } else {
            holder.set(value);
        }

        return holder;
    }

}
