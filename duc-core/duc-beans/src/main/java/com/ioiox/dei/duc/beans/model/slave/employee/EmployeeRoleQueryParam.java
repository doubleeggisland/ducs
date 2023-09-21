package com.ioiox.dei.duc.beans.model.slave.employee;

import com.ioiox.dei.duc.beans.model.slave.SimpleRoleQueryParam;
import com.ioiox.dei.duc.beans.model.slave.SimpleRoleQueryParamBuilder;

public class EmployeeRoleQueryParam
        extends SimpleRoleQueryParam {

    private EmployeeRoleQueryParam(final Builder builder) {
        super(builder);
    }

    public static class Builder
            extends SimpleRoleQueryParamBuilder<EmployeeRoleQueryParam> {

        @Override
        public EmployeeRoleQueryParam build() {
            return new EmployeeRoleQueryParam(this);
        }
    }
}
