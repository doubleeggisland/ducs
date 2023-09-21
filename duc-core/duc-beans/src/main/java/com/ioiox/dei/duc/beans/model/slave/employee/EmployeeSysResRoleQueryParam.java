package com.ioiox.dei.duc.beans.model.slave.employee;

import com.ioiox.dei.duc.beans.model.slave.SimpleRoleQueryParam;
import com.ioiox.dei.duc.beans.model.slave.SimpleRoleQueryParamBuilder;

public class EmployeeSysResRoleQueryParam extends SimpleRoleQueryParam {

    private EmployeeSysResRoleQueryParam(final Builder builder) {
        super(builder);
    }

    public static class Builder
            extends SimpleRoleQueryParamBuilder<EmployeeSysResRoleQueryParam> {

        @Override
        public EmployeeSysResRoleQueryParam build() {
            return new EmployeeSysResRoleQueryParam(this);
        }
    }
}
