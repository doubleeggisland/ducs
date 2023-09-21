package com.ioiox.dei.duc.beans.model.master.user;

public class UserAcctSysResRoleDelParam extends SimpleUserRoleDelParam {

    private UserAcctSysResRoleDelParam(final Builder builder) {
        super(builder);
    }

    public static class Builder
            extends SimpleUserRoleDelParamBuilder<UserAcctSysResRoleDelParam> {

        @Override
        public UserAcctSysResRoleDelParam build() {
            return new UserAcctSysResRoleDelParam(this);
        }
    }
}
