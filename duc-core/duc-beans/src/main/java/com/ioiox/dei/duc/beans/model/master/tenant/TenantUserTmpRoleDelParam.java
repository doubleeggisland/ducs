package com.ioiox.dei.duc.beans.model.master.tenant;

public class TenantUserTmpRoleDelParam
        extends SimpleTenantUserRoleDelParam {

    private TenantUserTmpRoleDelParam(final Builder builder) {
        super(builder);
    }

    public static class Builder
            extends SimpleTenantUserRoleDelParamBuilder<TenantUserTmpRoleDelParam> {

        @Override
        public TenantUserTmpRoleDelParam build() {
            return new TenantUserTmpRoleDelParam(this);
        }
    }
}
