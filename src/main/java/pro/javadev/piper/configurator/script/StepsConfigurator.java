package pro.javadev.piper.configurator.script;

import pro.javadev.piper.common.Holder;
import pro.javadev.piper.configurator.BaseConfigurator;
import pro.javadev.piper.configurator.Keyword;
import pro.javadev.piper.execution.script.CommandLine;
import pro.javadev.piper.execution.script.DefaultCommandLine;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class StepsConfigurator extends BaseConfigurator {

    @Override
    public void handle(Holder node, Holder target) {
        validateHolder(target, List.class);
        target.<List<CommandLine>>get()
                .addAll(node.<List<Holder>>get().stream()
                        .map(Holder::<String>get).map(DefaultCommandLine::new)
                        .collect(Collectors.toList()));
    }

    @Override
    public Predicate<Holder> applicable() {
        return super.applicable()
                .and(holder -> Keyword.STEPS.match(holder.inner().get()));
    }

}
