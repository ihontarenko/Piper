package io.gobbler.commander.converter;

import io.gobbler.commander.Converter;

import java.util.HashMap;
import java.util.Map;

public class KeyAbbreviationConverter implements Converter<Map<String, Object>, Map<String, Object>> {

    private final Map<String, String> abbreviations;

    public KeyAbbreviationConverter(Map<String, String> abbreviations) {
        this.abbreviations = abbreviations;
    }

    @Override
    public Map<String, Object> convert(Map<String, Object> source) {
        return abbreviation(source, new HashMap<>());
    }

    private Map<String, Object> abbreviation(Map<String, Object> source, Map<String, Object> target) {
        source.forEach((key, values) -> {
            String name = key;

            if (abbreviations.containsKey(key)) {
                name = abbreviations.get(key);
            }

            if (values instanceof Map) {
                target.put(name, abbreviation((Map<String, Object>) values, new HashMap<>()));
            } else {
                target.put(name, values);
            }
        });

        return target;
    }

}
