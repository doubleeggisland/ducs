package com.ioiox.dei.duc.beans.model.slave.user;

public class UserAcctTmpSysResRoleQueryParam
        extends SimpleUserRoleQueryParam {

    private UserAcctTmpSysResRoleQueryParam(final Builder builder) {
        super(builder);
    }

    public static class Builder
            extends SimpleUserRoleQueryParamBuilder<UserAcctTmpSysResRoleQueryParam> {

        @Override
        public UserAcctTmpSysResRoleQueryParam build() {
            return new UserAcctTmpSysResRoleQueryParam(this);
        }
    }
}
