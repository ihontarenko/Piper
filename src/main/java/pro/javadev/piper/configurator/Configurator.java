package pro.javadev.piper.configurator;

import java.util.function.Predicate;

public interface Configurator<V, X> {

    Predicate<V> applicable();

    void handle(V value, X context);

}
