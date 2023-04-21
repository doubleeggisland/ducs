package com.ioiox.dei.duc.beans.vo.std.master;

import com.ioiox.dei.core.vo.StdDataDelParamBuilder;

import java.util.List;

public abstract class UserDelParamBuilder<T extends UserDelParam>
        extends StdDataDelParamBuilder<T> {
    private List<String> statuses;

    public UserDelParamBuilder<T> statuses(final List<String> statuses) {
        this.statuses = statuses;
        return this;
    }

    public List<String> statuses() {
        return statuses;
    }
}
