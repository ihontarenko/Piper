package pro.javadev.piper.common;

public interface Dumper<F, T> {
    T dump(F source);
}
