package pro.javadev.piper.script;

import pro.javadev.piper.common.KeyValues;
import pro.javadev.piper.execution.Validator;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

public class DefaultScript implements Script {

    private final List<CommandLine> steps       = new ArrayList<>();
    private final List<Validator>   validators  = new ArrayList<>();
    private       KeyValues<String> environment = new KeyValues<>();
    private       String            name;
    private       String            description;

    public DefaultScript(String name) {
        this.name = name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getKey() {
        return getName();
    }

    @Override
    public List<CommandLine> getLines() {
        return steps;
    }

    @Override
    public void setLines(CommandLine... steps) {
        this.steps.addAll(List.of(steps));
    }

    @Override
    public List<Validator> getValidators() {
        return validators;
    }

    @Override
    public void setValidator(Validator... validator) {
        validators.addAll(List.of(validator));
    }

    @Override
    public KeyValues<String> getLocalProperties() {
        return environment;
    }

    @Override
    public void setLocalProperties(KeyValues<String> environment) {
        this.environment = environment;
    }

    @Override
    public String toString() {
        return format("SCRIPT[%s, steps=%s]", name, steps);
    }

}
