package com.ioiox.dei.duc.beans.model.slave.tenant;

public class TenantUserTmpSysResRoleQueryParam
        extends SimpleTenantUserRoleQueryParam {

    private TenantUserTmpSysResRoleQueryParam(final Builder builder) {
        super(builder);
    }

    public static class Builder
            extends SimpleTenantUserRoleQueryParamBuilder<TenantUserTmpSysResRoleQueryParam> {

        @Override
        public TenantUserTmpSysResRoleQueryParam build() {
            return new TenantUserTmpSysResRoleQueryParam(this);
        }
    }
}
