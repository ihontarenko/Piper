package pro.javadev.piper.configurator;

import pro.javadev.piper.common.Holder;

import java.util.function.Predicate;

public class ConfiguratorParser extends BaseConfigurator {

    @Override
    public void handle(Holder node, Holder target) {
        System.out.println("TargetParser: " + node);
    }

    @Override
    public Predicate<Holder> applicable() {
        return super.applicable()
                .and(value -> Keyword.DESCRIPTION.match(value.inner().get()));
    }

}
