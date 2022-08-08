package pro.javadev.piper.execution;

import org.apache.commons.text.StringSubstitutor;
import pro.javadev.piper.ApplicationContext;
import pro.javadev.piper.common.Converter;
import pro.javadev.piper.converter.FlattenMapConverter;
import pro.javadev.piper.converter.KeyAbbreviationConverter;
import pro.javadev.piper.execution.script.Script;

import java.util.HashMap;
import java.util.Map;

public class CommandLineCompleter implements Converter<String, String> {

    private final ApplicationContext context;
    private final Script             script;

    public CommandLineCompleter(ApplicationContext context, Script script) {
        this.script = script;
        this.context = context;
    }

    private Map<String, Object> flatten() {
        return new HashMap<>() {{
            putAll(new FlattenMapConverter("env:", ".").convert(
                    System.getenv()
            ));
            putAll(new FlattenMapConverter(null, ".").convert(
                    new KeyAbbreviationConverter(new HashMap<>() {{
                        put("properties", "ppty");
                        put("config", "cfg");
                    }}).convert(context.getValues())
            ));
            putAll(new FlattenMapConverter(null, ".").convert(
                    script.getLocalProperties()
            ));
            putAll(new FlattenMapConverter(null, ".").convert(
                    new HashMap<>() {{
                        put("ARGS", context.getArguments());
                    }}
            ));
        }};
    }

    @Override
    public String convert(String input) {
        return StringSubstitutor.replace(input, flatten());
    }

}
