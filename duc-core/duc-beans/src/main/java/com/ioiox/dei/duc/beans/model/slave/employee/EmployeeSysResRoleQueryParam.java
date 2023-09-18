package com.ioiox.dei.duc.beans.model.slave.employee;

import com.ioiox.dei.duc.beans.model.slave.RoleQueryParam;
import com.ioiox.dei.duc.beans.model.slave.RoleQueryParamBuilder;

public class EmployeeSysResRoleQueryParam extends RoleQueryParam {

    private EmployeeSysResRoleQueryParam(final Builder builder) {
        super(builder);
    }

    public static class Builder
            extends RoleQueryParamBuilder<EmployeeSysResRoleQueryParam> {

        @Override
        public EmployeeSysResRoleQueryParam build() {
            return new EmployeeSysResRoleQueryParam(this);
        }
    }
}
