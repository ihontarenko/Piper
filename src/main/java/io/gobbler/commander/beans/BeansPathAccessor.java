package io.gobbler.commander.beans;

import org.apache.commons.beanutils.PropertyUtilsBean;

import java.lang.reflect.InvocationTargetException;
import java.util.function.Supplier;
import java.util.logging.Logger;

import static java.lang.String.format;
import static java.util.logging.Logger.getLogger;

public class BeansPathAccessor {

    private static final PropertyUtilsBean PROPERTY_UTILS_BEAN;
    private static final Logger LOGGER;

    static {
        PROPERTY_UTILS_BEAN = new PropertyUtilsBean();
        LOGGER = getLogger(BeansPathAccessor.class.getName());
    }

    public void setProperty(Object bean, String name, Object value) {
        try {
            PROPERTY_UTILS_BEAN.setProperty(bean, name, value);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LOGGER.warning(() -> format("Unable to 'SET' property value by path: '%s'", name));
        }
    }

    public <T> T getProperty(Object bean, String name, Class<T> type) {
        T value = null;

        try {
            value = (T) PROPERTY_UTILS_BEAN.getProperty(bean, name);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            LOGGER.warning(() -> format("Unable to 'GET' property value by path: '%s'", name));
        }

        return type == null ? value : type.cast(value);
    }

    public <T> T getProperty(Object bean, String name, Supplier<T> defaultValue, Class<T> type) {
        T value = getProperty(bean, name, type);

        if (value == null) {
            value = defaultValue != null ? defaultValue.get() : null;
        }

        return value;
    }

}
