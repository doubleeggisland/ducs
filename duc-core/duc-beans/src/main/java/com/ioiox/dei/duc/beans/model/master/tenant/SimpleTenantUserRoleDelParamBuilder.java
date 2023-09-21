package com.ioiox.dei.duc.beans.model.master.tenant;

import com.ioiox.dei.duc.beans.model.master.SimpleRoleDelParamBuilder;

import java.util.List;

public abstract class SimpleTenantUserRoleDelParamBuilder<T extends SimpleTenantUserRoleDelParam>
        extends SimpleRoleDelParamBuilder<T> {

    private List<Long> tenantIds;

    public SimpleTenantUserRoleDelParamBuilder<T> tenantIds(final List<Long> tenantIds) {
        this.tenantIds = tenantIds;
        return this;
    }

    public List<Long> tenantIds() {
        return tenantIds;
    }
}
