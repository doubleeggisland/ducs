package com.ioiox.dei.duc.beans.model.slave.user;

public class UserAcctTmpSysResRoleQueryParam
        extends BaseUserRoleQueryParam {

    private UserAcctTmpSysResRoleQueryParam(final Builder builder) {
        super(builder);
    }

    public static class Builder
            extends BaseUserRoleQueryParamBuilder<UserAcctTmpSysResRoleQueryParam> {

        @Override
        public UserAcctTmpSysResRoleQueryParam build() {
            return new UserAcctTmpSysResRoleQueryParam(this);
        }
    }
}
