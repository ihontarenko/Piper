package pro.javadev.piper.common;

public interface Parser<S, R> {
    R parse(S stream);
}
