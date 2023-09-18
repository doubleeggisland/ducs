package com.ioiox.dei.duc.beans.model.slave.employee;

import com.ioiox.dei.duc.beans.model.slave.RoleQueryParam;
import com.ioiox.dei.duc.beans.model.slave.RoleQueryParamBuilder;

public class EmployeeTmpRoleQueryParam extends RoleQueryParam {

    private EmployeeTmpRoleQueryParam(final Builder builder) {
        super(builder);
    }

    public static class Builder
            extends RoleQueryParamBuilder<EmployeeTmpRoleQueryParam> {

        @Override
        public EmployeeTmpRoleQueryParam build() {
            return new EmployeeTmpRoleQueryParam(this);
        }
    }
}
