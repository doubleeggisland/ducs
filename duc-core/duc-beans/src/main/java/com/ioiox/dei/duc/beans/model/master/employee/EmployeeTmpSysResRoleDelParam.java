package com.ioiox.dei.duc.beans.model.master.employee;

import com.ioiox.dei.duc.beans.model.master.SimpleRoleDelParam;
import com.ioiox.dei.duc.beans.model.master.SimpleRoleDelParamBuilder;

public class EmployeeTmpSysResRoleDelParam
        extends SimpleRoleDelParam {

    private EmployeeTmpSysResRoleDelParam(final Builder builder) {
        super(builder);
    }

    public static class Builder
            extends SimpleRoleDelParamBuilder<EmployeeTmpSysResRoleDelParam> {

        @Override
        public EmployeeTmpSysResRoleDelParam build() {
            return new EmployeeTmpSysResRoleDelParam(this);
        }
    }
}
