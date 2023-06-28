package com.ioiox.dei.duc.beans.model.slave;

import com.ioiox.dei.core.vo.StdDataQueryParamBuilder;

import java.util.List;

public abstract class RoleQueryParamBuilder<T extends RoleQueryParam>
        extends StdDataQueryParamBuilder<T> {
    private List<String> codes;
    private List<Long> sysPrjIds;
    private List<String> statuses;

    public RoleQueryParamBuilder<T> codes(final List<String> codes) {
        this.codes = codes;
        return this;
    }
    public RoleQueryParamBuilder<T> sysPrjIds(final List<Long> sysPrjIds) {
        this.sysPrjIds = sysPrjIds;
        return this;
    }
    public RoleQueryParamBuilder<T> statuses(final List<String> statuses) {
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
