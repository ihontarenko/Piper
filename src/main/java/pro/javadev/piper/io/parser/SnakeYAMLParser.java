package pro.javadev.piper.io.parser;

import pro.javadev.piper.common.Parser;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class SnakeYAMLParser implements Parser<InputStream, Map<String, Object>> {

    private static final Yaml YAML = new Yaml();

    @Override
    public Map<String, Object> parse(InputStream stream) {
        return YAML.load(stream);
    }

}
