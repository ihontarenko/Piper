package io.gobbler.commander.composite;

import java.util.ArrayList;
import java.util.List;

abstract public class AbstractComponent<I, C extends Component.Context> implements Component<I, C> {

    protected List<Component<Object, C>> children = new ArrayList<>();

    @Override
    public void addChild(Component<Object, C> child) {
        children.add(child);
    }

}
