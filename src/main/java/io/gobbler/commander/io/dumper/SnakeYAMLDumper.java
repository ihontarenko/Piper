package io.gobbler.commander.io.dumper;

import io.gobbler.commander.io.Dumper;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.util.Map;

import static org.yaml.snakeyaml.DumperOptions.FlowStyle.BLOCK;

public class SnakeYAMLDumper implements Dumper<Map<?, ?>, String> {

    private static final Yaml YAML;

    static {
        DumperOptions options = new DumperOptions();

        options.setDefaultFlowStyle(BLOCK);
        options.setIndent(2);
        options.setPrettyFlow(true);

        YAML = new Yaml(options);
    }

    @Override
    public String dump(Map<?, ?> source) {
        return YAML.dump(source);
    }

}
