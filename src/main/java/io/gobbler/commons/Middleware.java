package io.gobbler.commons;

public interface Middleware<V> {

    Middleware<V> next(Middleware<V> next);

    boolean has();

    boolean proceed(V target);

    Middleware<V> next();

    default boolean next(V target) {
        boolean result = proceed(target);

        if (result && has()) {
            next().next(target);
        }

        return result;
    }

}
