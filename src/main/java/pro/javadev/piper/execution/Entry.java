package pro.javadev.piper.execution;

public interface Entry {

    String line();

    int ordinal();

    boolean isFirst();

    boolean isBlank();

}
