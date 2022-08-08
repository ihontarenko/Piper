package pro.javadev.piper.execution;

import java.util.Map;

@FunctionalInterface
public interface Validator {

    void validate(Entry entry, ExecutionContext context);

    default void configure(Map<String, Object> parameters) {

    }

    default Validator chain(Validator that) {
        return (entry, context) -> {
            this.validate(entry, context);
            that.validate(entry, context);
        };
    }

}
