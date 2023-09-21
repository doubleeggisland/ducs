package com.ioiox.dei.duc.beans.model.master.tenant;

public class TenantUserSysResRoleDelParam
        extends SimpleTenantUserRoleDelParam {

    private TenantUserSysResRoleDelParam(final Builder builder) {
        super(builder);
    }

    public static class Builder
            extends SimpleTenantUserRoleDelParamBuilder<TenantUserSysResRoleDelParam> {

        @Override
        public TenantUserSysResRoleDelParam build() {
            return new TenantUserSysResRoleDelParam(this);
        }
    }
}
