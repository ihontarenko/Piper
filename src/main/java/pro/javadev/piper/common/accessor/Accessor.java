package pro.javadev.piper.common.accessor;

public interface Accessor {

    <R> R read(Object target, String name);

    <R> void write(R target, String name, Object value);

    boolean isReadable(Object target, String name);

    boolean isWritable(Object target, String name);

}
