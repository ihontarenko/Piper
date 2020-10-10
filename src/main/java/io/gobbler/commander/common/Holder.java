package io.gobbler.commander.common;

import static java.lang.String.format;
import static java.util.Objects.hash;
import static java.util.Objects.requireNonNull;

@SuppressWarnings({"unchecked"})
public class Holder {

    protected Holder inner;
    protected Object value;

    Holder(Object value, Object inner) {
        this(value);
        this.inner = new Holder(inner);
    }

    Holder(Object value) {
        this.value = value;
    }

    public static Holder of(Object value, Object inner) {
        return new Holder(value, inner);
    }

    public static Holder of(Object value) {
        return of(value, null);
    }

    public <V> V get() {
        return (V) value;
    }

    public <V> void set(V value) {
        this.value = value;
    }

    public Holder inner() {
        return this.inner;
    }

    public Class<?> type() {
        return get().getClass();
    }

    public boolean is(Class<?> type) {
        requireNonNull(type, "UNABLE COMPARE HOLDER VALUE TYPE WITH NULL");

        return value != null && type.isAssignableFrom(type());
    }

    @Override
    public int hashCode() {
        return hash(value, inner);
    }

    @Override
    public String toString() {
        return format("HOLDER: {value=%s, type=%s, inner=%s}", value, value != null ? type().getName() : "?", inner());
    }
}
