package com.ioiox.dei.duc.beans.model.slave.user;

public class UserAcctTmpRoleQueryParam
        extends SimpleUserRoleQueryParam {

    private UserAcctTmpRoleQueryParam(final Builder builder) {
        super(builder);
    }

    public static class Builder
            extends SimpleUserRoleQueryParamBuilder<UserAcctTmpRoleQueryParam> {

        @Override
        public UserAcctTmpRoleQueryParam build() {
            return new UserAcctTmpRoleQueryParam(this);
        }
    }
}
