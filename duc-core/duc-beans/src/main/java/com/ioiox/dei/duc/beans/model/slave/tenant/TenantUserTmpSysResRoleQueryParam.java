package com.ioiox.dei.duc.beans.model.slave.tenant;

public class TenantUserTmpSysResRoleQueryParam
        extends BaseTenantUserRoleQueryParam {

    private TenantUserTmpSysResRoleQueryParam(final Builder builder) {
        super(builder);
    }

    public static class Builder
            extends BaseTenantUserRoleQueryParamBuilder<TenantUserTmpSysResRoleQueryParam> {

        @Override
        public TenantUserTmpSysResRoleQueryParam build() {
            return new TenantUserTmpSysResRoleQueryParam(this);
        }
    }
}
