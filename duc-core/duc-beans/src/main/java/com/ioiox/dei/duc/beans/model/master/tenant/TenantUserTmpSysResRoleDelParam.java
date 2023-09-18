package com.ioiox.dei.duc.beans.model.master.tenant;

public class TenantUserTmpSysResRoleDelParam
        extends BaseTenantUserRoleDelParam {

    private TenantUserTmpSysResRoleDelParam(final Builder builder) {
        super(builder);
    }

    public static class Builder
            extends BaseTenantUserRoleDelParamBuilder<TenantUserTmpSysResRoleDelParam> {

        @Override
        public TenantUserTmpSysResRoleDelParam build() {
            return new TenantUserTmpSysResRoleDelParam(this);
        }
    }
}
