package pro.javadev.piper.configurator;

import pro.javadev.piper.Version;
import pro.javadev.piper.common.Holder;

import java.util.function.Predicate;

import static pro.javadev.piper.configurator.Keyword.VERSION;

public class VersionConfigurator extends BaseConfigurator {

    @Override
    public void handle(Holder node, Holder target) {
        if (target.is(Version.class)) {
            target.<Version>get().setVersion(node.get());
        } else {
            throw new TypeMismatchException(this, Version.class, target.type());
        }
    }

    @Override
    public Predicate<Holder> applicable() {
        return super.applicable().and(value -> VERSION.match(value.inner().get()));
    }

}
