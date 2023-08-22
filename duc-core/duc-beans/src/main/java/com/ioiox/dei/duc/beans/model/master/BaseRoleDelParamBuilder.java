package com.ioiox.dei.duc.beans.model.master;

import com.ioiox.dei.core.orm.mybatis.model.std.data.StdDataDelParamBuilder;

import java.util.List;

public abstract class BaseRoleDelParamBuilder<T extends BaseRoleDelParam>
        extends StdDataDelParamBuilder<T> {
    private List<Long> sysPrjIds;
    private List<String> statuses;

    public BaseRoleDelParamBuilder<T> sysPrjIds(final List<Long> sysPrjIds) {
        this.sysPrjIds = sysPrjIds;
        return this;
    }
    public BaseRoleDelParamBuilder<T> statuses(final List<String> statuses) {
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
