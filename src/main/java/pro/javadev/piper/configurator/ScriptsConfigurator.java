package pro.javadev.piper.configurator;

import pro.javadev.piper.common.Holder;
import pro.javadev.piper.common.Registry;
import pro.javadev.piper.script.DefaultScript;
import pro.javadev.piper.script.Script;

import java.util.Map;

import static pro.javadev.piper.common.Holder.of;

public class ScriptsConfigurator extends BaseConfigurator {

    @Override
    public void handle(Holder node, Holder target) {
        validateHolder(target, Registry.class);

        Registry<String, Script> registry = target.get();

        for (Map.Entry<String, Holder> entry : node.<Map<String, Holder>>get().entrySet()) {
            registry.register(new DefaultScript(entry.getKey()));
            find(ScriptConfigurator.class).get(0).handle(entry.getValue(), of(registry.get(entry.getKey())));
        }

    }

}
