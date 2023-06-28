package com.ioiox.dei.duc.beans.model.master.user;

public class UserAcctRoleDelParam extends BaseUserRoleDelParam {

    private UserAcctRoleDelParam(final Builder builder) {
        super(builder);
    }

    public static class Builder
            extends BaseUserRoleDelParamBuilder<UserAcctRoleDelParam> {

        @Override
        public UserAcctRoleDelParam build() {
            return new UserAcctRoleDelParam(this);
        }
    }
}
