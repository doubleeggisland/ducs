package com.ioiox.dei.duc.beans.vo.std.slave.user;

public class UserAcctSysResRoleQueryParam
        extends BaseUserRoleQueryParam {

    private UserAcctSysResRoleQueryParam(final Builder builder) {
        super(builder);
    }

    public static class Builder
            extends BaseUserRoleQueryParamBuilder<UserAcctSysResRoleQueryParam> {

        @Override
        public UserAcctSysResRoleQueryParam build() {
            return new UserAcctSysResRoleQueryParam(this);
        }
    }
}
