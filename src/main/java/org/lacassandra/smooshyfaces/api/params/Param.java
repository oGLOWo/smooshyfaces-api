package org.lacassandra.smooshyfaces.api.params;

import com.google.common.base.Preconditions;

public abstract class Param<T> {
    protected T actual;

    protected Param() {

    }

    protected Param(final T actual) {
        Preconditions.checkNotNull(actual);
        this.actual = actual;
    }

    public T get() {
        return actual;
    }

    public boolean isDefault() {
        return false;
    }
}
