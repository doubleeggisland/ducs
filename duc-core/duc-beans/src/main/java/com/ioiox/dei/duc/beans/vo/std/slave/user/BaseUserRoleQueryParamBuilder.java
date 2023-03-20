package com.ioiox.dei.duc.beans.vo.std.slave.user;

import com.ioiox.dei.duc.beans.vo.std.slave.RoleQueryParamBuilder;

import java.util.List;

public abstract class BaseUserRoleQueryParamBuilder<T extends BaseUserRoleQueryParam>
        extends RoleQueryParamBuilder<T> {
    private List<Long> corpIds;
    private List<Long> tenantIds;

    public BaseUserRoleQueryParamBuilder<T> corpIds(final List<Long> corpIds) {
        this.corpIds = corpIds;
        return this;
    }
    public BaseUserRoleQueryParamBuilder<T> tenantIds(final List<Long> tenantIds) {
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
