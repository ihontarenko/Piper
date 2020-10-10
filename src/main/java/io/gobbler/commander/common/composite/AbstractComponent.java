package io.gobbler.commander.common.composite;

import java.util.ArrayList;
import java.util.List;

abstract public class AbstractComponent<S, C extends Component<?, C, X>, X> implements Component<S, C, X> {

    protected List<C> children = new ArrayList<>();

    @Override
    public C addChild(C child) {
        children.add(child);

        return (C) this;
    }

}
