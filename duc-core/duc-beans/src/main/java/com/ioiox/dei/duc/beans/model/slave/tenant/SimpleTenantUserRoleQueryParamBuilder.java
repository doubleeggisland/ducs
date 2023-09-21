package com.ioiox.dei.duc.beans.model.slave.tenant;

import com.ioiox.dei.duc.beans.model.slave.SimpleRoleQueryParamBuilder;

import java.util.List;

public abstract class SimpleTenantUserRoleQueryParamBuilder<T extends SimpleTenantUserRoleQueryParam>
        extends SimpleRoleQueryParamBuilder<T> {
    private List<Long> tenantIds;

    public SimpleTenantUserRoleQueryParamBuilder<T> tenantIds(final List<Long> tenantIds) {
        this.tenantIds = tenantIds;
        return this;
    }

    public List<Long> tenantIds() {
        return tenantIds;
    }
}
