package com.ioiox.dei.duc.beans.model.master.tenant;

public class TenantUserTmpRoleDelParam
        extends BaseTenantUserRoleDelParam {

    private TenantUserTmpRoleDelParam(final Builder builder) {
        super(builder);
    }

    public static class Builder
            extends BaseTenantUserRoleDelParamBuilder<TenantUserTmpRoleDelParam> {

        @Override
        public TenantUserTmpRoleDelParam build() {
            return new TenantUserTmpRoleDelParam(this);
        }
    }
}
