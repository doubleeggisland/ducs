package com.ioiox.dei.duc.beans.model.master;

import com.ioiox.dei.core.orm.mybatis.model.std.data.StdDataDelParamBuilder;

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
