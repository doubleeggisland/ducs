package com.ioiox.dei.duc.beans.model.master.user;

import com.ioiox.dei.duc.beans.model.master.BaseRoleDelParamBuilder;

import java.util.List;

public abstract class BaseUserRoleDelParamBuilder<T extends BaseUserRoleDelParam>
        extends BaseRoleDelParamBuilder<T> {
    private List<Long> corpIds;
    private List<Long> tenantIds;

    public BaseUserRoleDelParamBuilder<T> corpIds(final List<Long> corpIds) {
        this.corpIds = corpIds;
        return this;
    }
    public BaseUserRoleDelParamBuilder<T> tenantIds(final List<Long> tenantIds) {
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
