package com.ioiox.dei.duc.beans.model.master.user;

import com.ioiox.dei.duc.beans.model.master.SimpleRoleDelParamBuilder;

import java.util.List;

public abstract class SimpleUserRoleDelParamBuilder<T extends SimpleUserRoleDelParam>
        extends SimpleRoleDelParamBuilder<T> {
    private List<Long> corpIds;
    private List<Long> tenantIds;

    public SimpleUserRoleDelParamBuilder<T> corpIds(final List<Long> corpIds) {
        this.corpIds = corpIds;
        return this;
    }
    public SimpleUserRoleDelParamBuilder<T> tenantIds(final List<Long> tenantIds) {
        this.tenantIds = tenantIds;
        return this;
    }

    public List<Long> corpIds() {
        return  corpIds;
    }
    public List<Long> tenantIds() {
        return  tenantIds;
    }
}
