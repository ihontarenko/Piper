package pro.javadev.piper.script;

import pro.javadev.piper.common.KeyValues;
import pro.javadev.piper.common.Registry;
import pro.javadev.piper.execution.Validator;

import java.util.List;

public interface Script extends Registry.KeyAware<String> {

    String getName();

    void setName(String name);

    String getDescription();

    void setDescription(String description);

    List<CommandLine> getLines();

    void setLines(CommandLine... steps);

    List<Validator> getValidators();

    void setValidator(Validator... validator);

    KeyValues<String> getLocalProperties();

    void setLocalProperties(KeyValues<String> values);

}
