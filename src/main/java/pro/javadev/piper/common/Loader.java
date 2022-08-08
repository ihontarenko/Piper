package pro.javadev.piper.common;

public interface Loader<I, O> {
    O load(I source) throws Exception;
}
