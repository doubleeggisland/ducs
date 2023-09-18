package com.ioiox.dei.duc.beans.model.slave.tenant;

import com.ioiox.dei.duc.beans.model.slave.RoleQueryParamBuilder;

import java.util.List;

public abstract class BaseTenantUserRoleQueryParamBuilder<T extends BaseTenantUserRoleQueryParam>
        extends RoleQueryParamBuilder<T> {
    private List<Long> tenantIds;

    public BaseTenantUserRoleQueryParamBuilder<T> tenantIds(final List<Long> tenantIds) {
        this.tenantIds = tenantIds;
        return this;
    }

    public List<Long> tenantIds() {
        return tenantIds;
    }
}
