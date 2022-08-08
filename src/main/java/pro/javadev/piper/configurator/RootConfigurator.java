package pro.javadev.piper.configurator;

import pro.javadev.piper.common.Holder;

import java.util.Map;
import java.util.function.Predicate;

import static java.util.function.Predicate.not;

public class RootConfigurator extends BaseConfigurator {

    @Override
    public Predicate<Holder> applicable() {
        return super.applicable().and(not(value -> value.<Map<?, ?>>get().isEmpty()));
    }

}
