package com.ioiox.dei.duc.beans.model.slave.user;

public class UserAcctTmpRoleQueryParam
        extends BaseUserRoleQueryParam {

    private UserAcctTmpRoleQueryParam(final Builder builder) {
        super(builder);
    }

    public static class Builder
            extends BaseUserRoleQueryParamBuilder<UserAcctTmpRoleQueryParam> {

        @Override
        public UserAcctTmpRoleQueryParam build() {
            return new UserAcctTmpRoleQueryParam(this);
        }
    }
}
