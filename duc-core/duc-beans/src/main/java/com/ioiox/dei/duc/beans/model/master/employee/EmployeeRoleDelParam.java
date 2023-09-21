package com.ioiox.dei.duc.beans.model.master.employee;

import com.ioiox.dei.duc.beans.model.master.SimpleRoleDelParam;
import com.ioiox.dei.duc.beans.model.master.SimpleRoleDelParamBuilder;

public class EmployeeRoleDelParam
        extends SimpleRoleDelParam {

    private EmployeeRoleDelParam(final Builder builder) {
        super(builder);
    }

    public static class Builder
            extends SimpleRoleDelParamBuilder<EmployeeRoleDelParam> {

        @Override
        public EmployeeRoleDelParam build() {
            return new EmployeeRoleDelParam(this);
        }
    }
}
