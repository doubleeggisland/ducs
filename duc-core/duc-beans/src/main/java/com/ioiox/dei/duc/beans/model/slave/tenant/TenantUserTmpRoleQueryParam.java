package com.ioiox.dei.duc.beans.model.slave.tenant;

public class TenantUserTmpRoleQueryParam
        extends BaseTenantUserRoleQueryParam {

    private TenantUserTmpRoleQueryParam(final Builder builder) {
        super(builder);
    }

    public static class Builder
            extends BaseTenantUserRoleQueryParamBuilder<TenantUserTmpRoleQueryParam> {

        @Override
        public TenantUserTmpRoleQueryParam build() {
            return new TenantUserTmpRoleQueryParam(this);
        }
    }
}
