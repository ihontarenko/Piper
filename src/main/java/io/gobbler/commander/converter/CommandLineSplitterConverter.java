package io.gobbler.commander.converter;

import io.gobbler.commander.common.Converter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.*;

public class CommandLineSplitterConverter implements Converter<String, String[]> {

    public static final int     PATTERN_FLAGS = MULTILINE | CASE_INSENSITIVE;
    public static final Pattern PATTERN_1     = compile("[\"']+([\\\"]+|[^\"']+)*?[\"']+|[^\\s+]+", PATTERN_FLAGS);
    public static final Pattern PATTERN_2     = compile("[^\\s]*\"(\\\\+\"|[^\"])*?\"|[^\\s]*'(\\\\+'|[^'])*?'|(\\\\\\s|[^\\s])+", PATTERN_FLAGS);

    private final Pattern primary;

    public CommandLineSplitterConverter(Pattern primary) {
        this.primary = primary;
    }

    public CommandLineSplitterConverter() {
        this(PATTERN_1);
    }

    @Override
    public String[] convert(String cmd) {
        String[] parsed = {};

        if (cmd != null && !cmd.isBlank()) {
            Matcher      matcher = primary.matcher(cmd.trim());
            List<String> matches = new ArrayList<>();

            while (matcher.find()) {
                matches.add(matcher.group());
            }

            parsed = matches.toArray(String[]::new);
        }

        return parsed;
    }

}
