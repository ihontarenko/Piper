package pro.javadev.piper.configurator;

import pro.javadev.piper.common.Holder;
import pro.javadev.piper.configurator.script.StepsConfigurator;
import pro.javadev.piper.configurator.script.ValidatorsConfigurator;
import pro.javadev.piper.execution.script.Script;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static pro.javadev.piper.common.Holder.of;
import static pro.javadev.piper.configurator.Keyword.*;

public class ScriptConfigurator extends BaseConfigurator {

    @Override
    public void handle(Holder node, Holder target) {
        validateHolder(target, Script.class);

        Script script = target.get();

        for (Map.Entry<String, Holder> entry : node.<Map<String, Holder>>get().entrySet()) {
            Keyword          keyword      = keyword(entry.getKey());
            BaseConfigurator configurator = configurator(keyword);

            switch (keyword) {
                case DESCRIPTION:
                    validateHolder(entry.getValue(), String.class);
                    Holder consumer = of((Consumer<String>) script::setDescription);
                    configurator.handle(entry.getValue(), consumer);
                    break;
                case STEPS:
                    validateHolder(entry.getValue(), List.class);
                    configurator.handle(entry.getValue(), of(script.getLines()));
                    break;
                case VALIDATORS:
                    validateHolder(entry.getValue(), List.class);
                    configurator.handle(entry.getValue(), of(script.getValidators()));
                    break;
                case ENVIRONMENT:
                    validateHolder(entry.getValue(), Map.class);
                    configurator.handle(entry.getValue(), of(script.getLocalProperties()));
                    break;
            }
        }
    }

    @Override
    public Map<Keyword, Class<?>> configurator() {
        return new EnumMap<>(Keyword.class) {{
            put(VALIDATORS, ValidatorsConfigurator.class);
            put(STEPS, StepsConfigurator.class);
            put(ENVIRONMENT, KeyValuesConfigurator.class);
            put(DESCRIPTION, TextConfigurator.class);
        }};
    }

    @Override
    public Keyword[] keywords() {
        return new Keyword[]{
                STEPS, VALIDATORS, DESCRIPTION, ENVIRONMENT
        };
    }
}
