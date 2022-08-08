package pro.javadev.piper.configurator;

import pro.javadev.piper.common.Holder;
import pro.javadev.piper.common.node.BaseNode;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.util.Objects.nonNull;
import static java.util.Objects.requireNonNull;

abstract public class BaseConfigurator extends BaseNode<BaseConfigurator> implements Configurator<Holder, Holder> {

    @Override
    public void handle(Holder node, Holder target) {
        requireNonNull(target, "VALUE CANNOT BE NULL");

        for (Map.Entry<String, Holder> entry : node.<Map<String, Holder>>get().entrySet()) {
            boolean undefined = true;

            for (Configurator<Holder, Holder> child : children) {
                if (child.applicable().test(entry.getValue())) {
                    child.handle(entry.getValue(), target);
                    undefined = false;
                    break;
                }
            }

            if (undefined) {
                throw new SyntaxErrorException(this, entry.getKey());
            }
        }
    }

    public void find(Holder node, Holder target) {
        Keyword  keyword = Keyword.find(node.inner().get());
        Class<?> type    = configurator().get(keyword);

        BaseConfigurator configurator = find(type).get(0);

    }

    public Map<Keyword, Class<?>> configurator() {
        return Collections.emptyMap();
    }

    public Keyword keyword(String name) {
        Keyword keyword = Keyword.find(name);

        if (keyword == null) {
            throw new SyntaxErrorException(this, name);
        }

        return keyword;
    }

    public BaseConfigurator configurator(Keyword keyword) {
        Class<?> type = configurator().get(keyword);

        if (type == null) {
            throw new UndefinedException(this, keyword);
        }

        List<BaseConfigurator> configurators = find(type);

        if (configurators.isEmpty()) {
            throw new UndefinedException(this, type, keyword);
        }

        return configurators.get(0);
    }

    public Keyword[] keywords() {
        return new Keyword[]{};
    }

    public void validateHolder(Holder target, Class<?> expected) {
        if (!target.is(expected)) {
            throw new TypeMismatchException(this, expected, target.type());
        }
    }

    public Predicate<Holder> applicable() {
        return ((Predicate<Holder>) Objects::nonNull).and(value -> nonNull(value.get())).and(value
                -> Stream.of(keywords()).anyMatch(
                keyword -> keyword.match(value.inner().get())));
    }

    @Override
    public String toString() {
        return format("CONFIGURATOR[%s]", this.getClass().getName());
    }
}
