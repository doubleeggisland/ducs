package com.ioiox.dei.duc.beans.model.slave.tenant;

public class TenantUserTmpRoleQueryParam
        extends SimpleTenantUserRoleQueryParam {

    private TenantUserTmpRoleQueryParam(final Builder builder) {
        super(builder);
    }

    public static class Builder
            extends SimpleTenantUserRoleQueryParamBuilder<TenantUserTmpRoleQueryParam> {

        @Override
        public TenantUserTmpRoleQueryParam build() {
            return new TenantUserTmpRoleQueryParam(this);
        }
    }
}
