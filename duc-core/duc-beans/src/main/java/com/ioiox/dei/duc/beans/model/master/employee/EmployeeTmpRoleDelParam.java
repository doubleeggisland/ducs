package com.ioiox.dei.duc.beans.model.master.employee;

import com.ioiox.dei.duc.beans.model.master.SimpleRoleDelParam;
import com.ioiox.dei.duc.beans.model.master.SimpleRoleDelParamBuilder;

public class EmployeeTmpRoleDelParam
        extends SimpleRoleDelParam {

    private EmployeeTmpRoleDelParam(final Builder builder) {
        super(builder);
    }

    public static class Builder
            extends SimpleRoleDelParamBuilder<EmployeeTmpRoleDelParam> {

        @Override
        public EmployeeTmpRoleDelParam build() {
            return new EmployeeTmpRoleDelParam(this);
        }
    }
}
