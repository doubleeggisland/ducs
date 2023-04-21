package com.ioiox.dei.duc.beans.vo.std.master.user;

public class UserAcctSysResRoleDelParam extends BaseUserRoleDelParam {

    private UserAcctSysResRoleDelParam(final Builder builder) {
        super(builder);
    }

    public static class Builder
            extends BaseUserRoleDelParamBuilder<UserAcctSysResRoleDelParam> {

        @Override
        public UserAcctSysResRoleDelParam build() {
            return new UserAcctSysResRoleDelParam(this);
        }
    }
}
