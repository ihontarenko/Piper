package io.gobbler.commander;

import java.util.Objects;
import java.util.function.Predicate;

public interface Processor<V, R> {

    R process(V source);

    Class<?> getType();

    default boolean isApplicable(V source) {
        Predicate<V> test = Objects::nonNull;

        return test.and(object -> object.getClass().isAssignableFrom(getType())).test(source);
    }

}
