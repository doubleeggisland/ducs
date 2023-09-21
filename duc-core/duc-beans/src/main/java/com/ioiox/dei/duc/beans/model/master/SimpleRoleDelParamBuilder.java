package com.ioiox.dei.duc.beans.model.master;

import com.ioiox.dei.core.orm.mybatis.model.std.data.StdDataDelParamBuilder;

import java.util.List;

public abstract class SimpleRoleDelParamBuilder<T extends SimpleRoleDelParam>
        extends StdDataDelParamBuilder<T> {
    private List<Long> sysPrjIds;
    private List<String> statuses;

    public SimpleRoleDelParamBuilder<T> sysPrjIds(final List<Long> sysPrjIds) {
        this.sysPrjIds = sysPrjIds;
        return this;
    }
    public SimpleRoleDelParamBuilder<T> statuses(final List<String> statuses) {
        this.statuses = statuses;
        return this;
    }

    public List<Long> sysPrjIds() {
        return sysPrjIds;
    }
    public List<String> statuses() {
        return statuses;
    }
}
