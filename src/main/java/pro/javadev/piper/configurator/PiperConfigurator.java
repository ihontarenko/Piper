package pro.javadev.piper.configurator;

import pro.javadev.piper.ApplicationContext;
import pro.javadev.piper.common.Holder;

import java.util.EnumMap;
import java.util.Map;

import static pro.javadev.piper.common.Holder.of;
import static pro.javadev.piper.configurator.Keyword.*;

public class PiperConfigurator extends BaseConfigurator {

    @Override
    public void handle(Holder node, Holder target) {
        Keyword          keyword      = Keyword.find(node.inner().get());
        BaseConfigurator configurator = configurator(keyword);

        validateHolder(target, ApplicationContext.class);

        ApplicationContext context = target.get();

        switch (keyword) {
            case VERSION:
                configurator.handle(node, of(context.getVersion()));
                break;
            case SCRIPTS:
                configurator.handle(node, of(context.getScripts()));
                break;
            case CONFIG:
                configurator.handle(node, of(context.getConfiguration()));
                break;
            case ENVIRONMENT:
                break;

        }
    }

    @Override
    public Map<Keyword, Class<?>> configurator() {
        return new EnumMap<>(Keyword.class) {{
            put(VERSION, VersionConfigurator.class);
            put(SCRIPTS, ScriptsConfigurator.class);
            put(ENVIRONMENT, KeyValuesConfigurator.class);
            put(CONFIG, KeyValuesConfigurator.class);
        }};
    }

    @Override
    public Keyword[] keywords() {
        return new Keyword[]{
                VERSION, SCRIPTS, ENVIRONMENT, CONFIG
        };
    }
}
