package io.gobbler.commander.composite;

import java.util.ArrayList;
import java.util.List;

abstract public class AbstractComponent<S, C extends Component<?, C, X>, X extends Component.Context> implements Component<S, C, X> {

    protected List<C> children = new ArrayList<>();

    @Override
    public void addChild(C child) {
        children.add(child);
    }

}
