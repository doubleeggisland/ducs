package com.ioiox.dei.duc.beans.model.slave.user;

public class UserAcctRoleQueryParam
        extends BaseUserRoleQueryParam {

    private UserAcctRoleQueryParam(final Builder builder) {
        super(builder);
    }

    public static class Builder
            extends BaseUserRoleQueryParamBuilder<UserAcctRoleQueryParam> {

        @Override
        public UserAcctRoleQueryParam build() {
            return new UserAcctRoleQueryParam(this);
        }
    }
}
