package com.ioiox.dei.duc.beans.model.slave.tenant;

public class TenantUserSysResRoleQueryParam
        extends SimpleTenantUserRoleQueryParam {

    private TenantUserSysResRoleQueryParam(final Builder builder) {
        super(builder);
    }

    public static class Builder
            extends SimpleTenantUserRoleQueryParamBuilder<TenantUserSysResRoleQueryParam> {

        @Override
        public TenantUserSysResRoleQueryParam build() {
            return new TenantUserSysResRoleQueryParam(this);
        }
    }
}
