package com.ioiox.dei.duc.beans.model.slave;

import com.ioiox.dei.core.orm.mybatis.model.std.data.StdDataQueryParamBuilder;

import java.util.List;

public abstract class SimpleRoleQueryParamBuilder<T extends SimpleRoleQueryParam>
        extends StdDataQueryParamBuilder<T> {
    private List<String> codes;
    private List<Long> sysPrjIds;
    private List<String> statuses;

    public SimpleRoleQueryParamBuilder<T> codes(final List<String> codes) {
        this.codes = codes;
        return this;
    }
    public SimpleRoleQueryParamBuilder<T> sysPrjIds(final List<Long> sysPrjIds) {
        this.sysPrjIds = sysPrjIds;
        return this;
    }
    public SimpleRoleQueryParamBuilder<T> statuses(final List<String> statuses) {
        this.statuses = statuses;
        return this;
    }

    public List<String> codes() {
        return codes;
    }

    public List<Long> sysPrjIds() {
        return sysPrjIds;
    }

    public List<String> statuses() {
        return statuses;
    }
}
