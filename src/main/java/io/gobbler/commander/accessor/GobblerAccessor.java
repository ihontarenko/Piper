package io.gobbler.commander.accessor;

import io.gobbler.commander.Accessor;
import io.gobbler.commander.Context;

public class GobblerAccessor {

    protected Accessor accessor;
    protected Context context;

    public GobblerAccessor(Accessor accessor, Context context) {
        this.accessor = accessor;
        this.context = context;
    }



}
