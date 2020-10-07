package io.gobbler.commander;

import io.gobbler.commons.Middleware;

abstract public class SingleMiddleware<V> implements Middleware<V> {

    protected Middleware<V> next;

    @Override
    public Middleware<V> next(Middleware<V> middleware) {
        next = middleware;

        return this;
    }

    @Override
    public boolean has() {
        return next != null;
    }

    @Override
    public Middleware<V> next() {
        return next;
    }

}
