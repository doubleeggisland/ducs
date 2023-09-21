package com.ioiox.dei.duc.beans.model.slave.tenant;

public class TenantUserRoleQueryParam
        extends SimpleTenantUserRoleQueryParam {

    private TenantUserRoleQueryParam(final Builder builder) {
        super(builder);
    }

    public static class Builder
            extends SimpleTenantUserRoleQueryParamBuilder<TenantUserRoleQueryParam> {

        @Override
        public TenantUserRoleQueryParam build() {
            return new TenantUserRoleQueryParam(this);
        }
    }
}
