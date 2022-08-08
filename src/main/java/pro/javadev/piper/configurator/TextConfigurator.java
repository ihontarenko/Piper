package pro.javadev.piper.configurator;

import pro.javadev.piper.common.Holder;

import java.util.function.Consumer;

public class TextConfigurator extends BaseConfigurator {

    @Override
    public void handle(Holder node, Holder target) {
        validateHolder(target, Consumer.class);
        target.<Consumer<String>>get().accept(node.get());
    }

}
