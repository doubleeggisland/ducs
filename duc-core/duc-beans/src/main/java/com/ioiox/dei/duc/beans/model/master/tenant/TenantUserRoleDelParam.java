package com.ioiox.dei.duc.beans.model.master.tenant;

public class TenantUserRoleDelParam
        extends BaseTenantUserRoleDelParam {

    private TenantUserRoleDelParam(final Builder builder) {
        super(builder);
    }

    public static class Builder
            extends BaseTenantUserRoleDelParamBuilder<TenantUserRoleDelParam> {

        @Override
        public TenantUserRoleDelParam build() {
            return new TenantUserRoleDelParam(this);
        }
    }
}
