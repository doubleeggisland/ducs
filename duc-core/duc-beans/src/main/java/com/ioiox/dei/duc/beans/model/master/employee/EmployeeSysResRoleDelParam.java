package com.ioiox.dei.duc.beans.model.master.employee;

import com.ioiox.dei.duc.beans.model.master.SimpleRoleDelParam;
import com.ioiox.dei.duc.beans.model.master.SimpleRoleDelParamBuilder;

public class EmployeeSysResRoleDelParam
        extends SimpleRoleDelParam {

    private EmployeeSysResRoleDelParam(final Builder builder) {
        super(builder);
    }

    public static class Builder
            extends SimpleRoleDelParamBuilder<EmployeeSysResRoleDelParam> {

        @Override
        public EmployeeSysResRoleDelParam build() {
            return new EmployeeSysResRoleDelParam(this);
        }
    }
}
