package io.gobbler.commander.composite;

public interface Component<S, C extends Component<?, C, X>, X extends Component.Context> {

    C addChild(C child);

    void handle(S value, X context);

    interface Context {

    }

}
