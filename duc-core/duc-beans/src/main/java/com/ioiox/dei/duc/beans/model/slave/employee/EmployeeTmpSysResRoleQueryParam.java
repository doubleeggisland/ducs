package com.ioiox.dei.duc.beans.model.slave.employee;

import com.ioiox.dei.duc.beans.model.slave.RoleQueryParam;
import com.ioiox.dei.duc.beans.model.slave.RoleQueryParamBuilder;

public class EmployeeTmpSysResRoleQueryParam extends RoleQueryParam {

    private EmployeeTmpSysResRoleQueryParam(final Builder builder) {
        super(builder);
    }

    public static class Builder
            extends RoleQueryParamBuilder<EmployeeTmpSysResRoleQueryParam> {

        @Override
        public EmployeeTmpSysResRoleQueryParam build() {
            return new EmployeeTmpSysResRoleQueryParam(this);
        }
    }
}
