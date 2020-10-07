package io.gobbler.commander.composite;

public interface Component<V, C extends Component.Context> {

    void addChild(Component<Object, C> child);

    void handle(V value, C context);

    interface Context {

    }

}
