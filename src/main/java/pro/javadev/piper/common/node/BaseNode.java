package pro.javadev.piper.common.node;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "unused"})
abstract public class BaseNode<N extends Node<N>> implements Node<N> {

    protected final List<N> children = new ArrayList<>();
    protected       Node<?> parent;

    public BaseNode() {
        this(null);
    }

    public BaseNode(N parent) {
        this.parent = parent;
    }

    @Override
    public boolean hasChildren() {
        return !children.isEmpty();
    }

    @Override
    public boolean hasParent() {
        return this.parent != null;
    }

    @Override
    public Node<?> parent() {
        return this.parent;
    }

    @Override
    public void parent(Node<?> node) {
        this.parent = node;
    }

    @Override
    public List<N> children() {
        return this.children;
    }

    @Override
    public boolean delete(N node) {
        return this.children.remove(node);
    }

    @Override
    public N add(N node) {
        if (this != node) {
            node.parent(this);
            this.children.add(node);
        }

        return (N) this;
    }

}
