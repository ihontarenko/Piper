package pro.javadev.piper.common;

public interface Converter<I, O> {
    O convert(I source);
}
