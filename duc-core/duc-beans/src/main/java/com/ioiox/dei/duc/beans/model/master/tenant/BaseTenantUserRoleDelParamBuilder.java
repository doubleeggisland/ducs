package com.ioiox.dei.duc.beans.model.master.tenant;

import com.ioiox.dei.duc.beans.model.master.BaseRoleDelParamBuilder;

import java.util.List;

public abstract class BaseTenantUserRoleDelParamBuilder<T extends BaseTenantUserRoleDelParam>
        extends BaseRoleDelParamBuilder<T> {

    private List<Long> tenantIds;

    public BaseTenantUserRoleDelParamBuilder<T> tenantIds(final List<Long> tenantIds) {
        this.tenantIds = tenantIds;
        return this;
    }

    public List<Long> tenantIds() {
        return tenantIds;
    }
}
