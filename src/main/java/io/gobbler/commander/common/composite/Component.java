package io.gobbler.commander.common.composite;

public interface Component<S, C extends Component<?, C, X>, X> {

    C addChild(C child);

    void handle(S value, X context);

}
