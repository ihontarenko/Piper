package pro.javadev.piper.common.node;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Predicate;

@SuppressWarnings({"unchecked", "unused"})
public interface Node<N extends Node<N>> {

    boolean hasChildren();

    boolean hasParent();

    default boolean isRoot() {
        return !hasParent();
    }

    Node<?> parent();

    void parent(Node<?> node);

    List<N> children();

    N add(N node);

    boolean delete(N node);

    default boolean exist(N node) {
        return Objects.nonNull(find(node));
    }

    default boolean exist(Class<?> type) {
        return !find(type).isEmpty();
    }

    default List<N> find(Class<?> type) {
        List<N>      result = new ArrayList<>();
        Predicate<N> tester = type::isInstance;
        N            self   = (N) this;

        if (hasChildren()) {
            for (N child : children()) {
                if (tester.test(child)) {
                    result.add(child);
                }
                result.addAll(child.find(type));
            }
        }

        return result;
    }

    default N find(Node<?> node) {
        N result = null;

        if (!equals(node)) {
            if (hasChildren()) {
                for (N child : children()) {
                    if ((result = child.equals(node) ? child : child.find(node)) != null) {
                        break;
                    }
                }
            }
        } else {
            result = (N) this;
        }

        return result;
    }

    default void execute(Consumer<N> executor) {
        executor.accept((N) this);

        if (hasChildren()) {
            for (N child : children()) {
                child.execute(executor);
            }
        }
    }

    enum Direction {
        IN_DEEP,
    }

}
