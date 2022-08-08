package pro.javadev.piper.configurator.script;

import pro.javadev.piper.common.Holder;
import pro.javadev.piper.configurator.BaseConfigurator;
import pro.javadev.piper.configurator.Keyword;
import pro.javadev.piper.converter.UnwrapFromHolderConverter;
import pro.javadev.piper.execution.Validator;
import pro.javadev.piper.execution.ValidatorType;
import pro.javadev.piper.execution.validator.ExpectedStringValidator;
import pro.javadev.piper.execution.validator.LineCounterValidator;
import pro.javadev.piper.execution.validator.WaitForValidator;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import static java.lang.String.format;
import static java.util.Objects.requireNonNull;
import static pro.javadev.piper.execution.ValidatorType.valueOf;

public class ValidatorsConfigurator extends BaseConfigurator {

    @Override
    public void handle(Holder node, Holder target) {
        validateHolder(target, List.class);

        for (Holder inner : node.<List<Holder>>get()) {
            validateHolder(inner, Map.class);

            Map<String, Holder> parameters = inner.get();
            Map<String, Holder> arguments  = parameters.get("parameters").get();
            Validator           validator  = createValidator(valueOf(parameters.get("type").get()));

            validator.configure(
                    new UnwrapFromHolderConverter().convert(arguments)
            );

            target.<List<Validator>>get().add(validator);
        }


    }

    private Validator createValidator(ValidatorType type) {
        Validator validator = null;

        switch (type) {
            case LINE_COUNT:
                validator = new LineCounterValidator();
                break;
            case EXPECTED_STRING:
                validator = new ExpectedStringValidator();
                break;
            case WAIT_FOR:
                validator = new WaitForValidator();
                break;
        }

        requireNonNull(validator, format("NOT VALIDATOR FOR TYPE %s", type));

        return validator;
    }

    @Override
    public Predicate<Holder> applicable() {
        return super.applicable().and(holder -> Keyword.VALIDATORS.match(holder.inner().get()));
    }

}
