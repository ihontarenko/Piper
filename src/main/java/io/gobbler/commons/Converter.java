package io.gobbler.commons;

public interface Converter<I, O> {
    O convert(I source);
}
