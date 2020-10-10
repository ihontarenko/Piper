package io.gobbler.commander.common;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;

public class Holder {

    private final Object value;

    Holder(Object value) {
        requireNonNull(value, "UNABLE TO CREATE HOLDER OBJECT WITH NULL VALUE");

        this.value = value;
    }

    @SuppressWarnings({"unchecked"})
    public <V> V get() {
        return (V) value;
    }

    public Class<?> type() {
        return get().getClass();
    }

    public boolean is(Class<?> type) {
        requireNonNull(value, "UNABLE COMPARE HOLDER VALUE TYPE WITH NULL");

        return type.isAssignableFrom(type());
    }

    public static Holder of(Object value) {
        return new Holder(value);
    }

    @Override
    public String toString() {
        return format("HOLDER: {value=%s, type=%s}", value, type().getName());
    }
}
