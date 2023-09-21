package com.ioiox.dei.duc.beans.model.slave.employee;

import com.ioiox.dei.duc.beans.model.slave.SimpleRoleQueryParam;
import com.ioiox.dei.duc.beans.model.slave.SimpleRoleQueryParamBuilder;

public class EmployeeTmpRoleQueryParam extends SimpleRoleQueryParam {

    private EmployeeTmpRoleQueryParam(final Builder builder) {
        super(builder);
    }

    public static class Builder
            extends SimpleRoleQueryParamBuilder<EmployeeTmpRoleQueryParam> {

        @Override
        public EmployeeTmpRoleQueryParam build() {
            return new EmployeeTmpRoleQueryParam(this);
        }
    }
}
