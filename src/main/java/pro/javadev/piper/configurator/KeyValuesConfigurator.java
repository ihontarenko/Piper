package pro.javadev.piper.configurator;

import pro.javadev.piper.common.KeyValues;
import pro.javadev.piper.common.Holder;

import java.util.Map;
import java.util.function.Predicate;

import static pro.javadev.piper.configurator.Keyword.ENVIRONMENT;
import static java.lang.String.format;

public class KeyValuesConfigurator extends BaseConfigurator {

    @Override
    public void handle(Holder node, Holder target) {
        if (target.is(KeyValues.class)) {
            node.<Map<String, Holder>>get().forEach((key, value)
                    -> target.<KeyValues<String>>get().put(key, value.get()));
        } else {
            throw new TypeMismatchException(this, KeyValues.class, target.type());
        }
    }

    @Override
    public Predicate<Holder> applicable() {
        return super.applicable()
                .and(value -> ENVIRONMENT.match(value.inner().get()));
    }

}
