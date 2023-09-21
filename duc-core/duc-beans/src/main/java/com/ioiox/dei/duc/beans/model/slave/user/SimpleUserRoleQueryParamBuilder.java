package com.ioiox.dei.duc.beans.model.slave.user;

import com.ioiox.dei.duc.beans.model.slave.SimpleRoleQueryParamBuilder;

import java.util.List;

public abstract class SimpleUserRoleQueryParamBuilder<T extends SimpleUserRoleQueryParam>
        extends SimpleRoleQueryParamBuilder<T> {
    private List<Long> corpIds;
    private List<Long> tenantIds;

    public SimpleUserRoleQueryParamBuilder<T> corpIds(final List<Long> corpIds) {
        this.corpIds = corpIds;
        return this;
    }
    public SimpleUserRoleQueryParamBuilder<T> tenantIds(final List<Long> tenantIds) {
        this.tenantIds = tenantIds;
        return this;
    }

    public List<Long> corpIds() {
        return corpIds;
    }
    public List<Long> tenantIds() {
        return tenantIds;
    }
}
