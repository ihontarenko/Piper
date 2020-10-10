package io.gobbler.commander.common;

public interface Converter<I, O> {
    O convert(I source);
}
