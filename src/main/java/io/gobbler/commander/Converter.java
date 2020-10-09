package io.gobbler.commander;

public interface Converter<I, O> {
    O convert(I source);
}
