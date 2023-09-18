package com.ioiox.dei.duc.beans.model.slave.employee;

import com.ioiox.dei.duc.beans.model.slave.RoleQueryParam;
import com.ioiox.dei.duc.beans.model.slave.RoleQueryParamBuilder;

public class EmployeeRoleQueryParam
        extends RoleQueryParam {

    private EmployeeRoleQueryParam(final Builder builder) {
        super(builder);
    }

    public static class Builder
            extends RoleQueryParamBuilder<EmployeeRoleQueryParam> {

        @Override
        public EmployeeRoleQueryParam build() {
            return new EmployeeRoleQueryParam(this);
        }
    }
}
