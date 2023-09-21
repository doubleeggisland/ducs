package com.ioiox.dei.duc.beans.model.slave.employee;

import com.ioiox.dei.duc.beans.model.slave.SimpleRoleQueryParam;
import com.ioiox.dei.duc.beans.model.slave.SimpleRoleQueryParamBuilder;

public class EmployeeTmpSysResRoleQueryParam extends SimpleRoleQueryParam {

    private EmployeeTmpSysResRoleQueryParam(final Builder builder) {
        super(builder);
    }

    public static class Builder
            extends SimpleRoleQueryParamBuilder<EmployeeTmpSysResRoleQueryParam> {

        @Override
        public EmployeeTmpSysResRoleQueryParam build() {
            return new EmployeeTmpSysResRoleQueryParam(this);
        }
    }
}
