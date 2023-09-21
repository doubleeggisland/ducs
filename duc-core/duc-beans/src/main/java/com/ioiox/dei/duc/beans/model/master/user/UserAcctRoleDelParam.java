package com.ioiox.dei.duc.beans.model.master.user;

public class UserAcctRoleDelParam extends SimpleUserRoleDelParam {

    private UserAcctRoleDelParam(final Builder builder) {
        super(builder);
    }

    public static class Builder
            extends SimpleUserRoleDelParamBuilder<UserAcctRoleDelParam> {

        @Override
        public UserAcctRoleDelParam build() {
            return new UserAcctRoleDelParam(this);
        }
    }
}
