package com.ioiox.dei.duc.beans.model.slave.user;

public class UserAcctSysResRoleQueryParam
        extends SimpleUserRoleQueryParam {

    private UserAcctSysResRoleQueryParam(final Builder builder) {
        super(builder);
    }

    public static class Builder
            extends SimpleUserRoleQueryParamBuilder<UserAcctSysResRoleQueryParam> {

        @Override
        public UserAcctSysResRoleQueryParam build() {
            return new UserAcctSysResRoleQueryParam(this);
        }
    }
}
