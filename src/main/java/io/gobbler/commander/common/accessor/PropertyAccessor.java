package io.gobbler.commander.common.accessor;

import io.gobbler.commander.common.beans.BeansPathAccessor;

import static java.util.Objects.nonNull;

public class PropertyAccessor implements Accessor {

    private static final BeansPathAccessor ACCESSOR = new BeansPathAccessor();

    @Override
    public <R> R read(Object target, String name) {
        return ACCESSOR.getProperty(target, name, null);
    }

    @Override
    public <R> void write(R target, String name, Object value) {
        ACCESSOR.setProperty(target, name, value);
    }

    @Override
    public boolean isReadable(Object target, String name) {
        return nonNull(ACCESSOR.getProperty(target, name, null));
    }

    @Override
    public boolean isWritable(Object target, String name) {
        return true;
    }

}
