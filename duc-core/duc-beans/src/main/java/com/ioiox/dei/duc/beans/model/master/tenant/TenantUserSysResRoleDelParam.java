package com.ioiox.dei.duc.beans.model.master.tenant;

public class TenantUserSysResRoleDelParam
        extends BaseTenantUserRoleDelParam {

    private TenantUserSysResRoleDelParam(final Builder builder) {
        super(builder);
    }

    public static class Builder
            extends BaseTenantUserRoleDelParamBuilder<TenantUserSysResRoleDelParam> {

        @Override
        public TenantUserSysResRoleDelParam build() {
            return new TenantUserSysResRoleDelParam(this);
        }
    }
}
