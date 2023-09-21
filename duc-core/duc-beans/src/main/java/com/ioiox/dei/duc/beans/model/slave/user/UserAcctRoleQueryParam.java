package com.ioiox.dei.duc.beans.model.slave.user;

public class UserAcctRoleQueryParam
        extends SimpleUserRoleQueryParam {

    private UserAcctRoleQueryParam(final Builder builder) {
        super(builder);
    }

    public static class Builder
            extends SimpleUserRoleQueryParamBuilder<UserAcctRoleQueryParam> {

        @Override
        public UserAcctRoleQueryParam build() {
            return new UserAcctRoleQueryParam(this);
        }
    }
}
