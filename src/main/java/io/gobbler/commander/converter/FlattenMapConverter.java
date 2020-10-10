package io.gobbler.commander.converter;

import io.gobbler.commander.common.Converter;

import java.util.AbstractMap.SimpleEntry;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class FlattenMapConverter implements Converter<Map<String, ?>, Map<String, Object>> {

    private static final String DEFAULT_PREFIX    = "data:";
    private static final String DEFAULT_SEPARATOR = ".";
    private final        String prefix;
    private final        String separator;

    public FlattenMapConverter() {
        this(DEFAULT_PREFIX, DEFAULT_SEPARATOR);
    }

    public FlattenMapConverter(String prefix, String separator) {
        this.prefix = prefix;
        this.separator = separator;
    }

    private Stream<Entry<String, Object>> flatten(Set<? extends Entry<String, ?>> source, String prefix) {
        return source.stream()
                .flatMap(entry -> {
                    Stream<Entry<String, Object>> stream;

                    if (entry.getValue() instanceof Map) {
                        stream = flatten(((Map<String, Object>) entry.getValue()).entrySet(), format("%s%s%s", prefix, entry.getKey(), separator));
                    } else if (entry.getValue() instanceof Collection) {
                        int[] counter = new int[]{0};
                        stream = flatten(((Collection<Object>) entry.getValue()).stream()
                                .collect(toMap(index -> format("[%d]", counter[0]++), identity())).entrySet(), format("%s%s", prefix, entry.getKey()));
                    } else {
                        stream = Stream.of(new SimpleEntry<>(format("%s%s", prefix, entry.getKey()), entry.getValue()));
                    }

                    return stream;
                });
    }

    @Override
    public Map<String, Object> convert(Map<String, ?> source) {
        return flatten(source.entrySet(), prefix == null ? "" : prefix)
                .collect(toMap(Entry::getKey, Entry::getValue));
    }

}
