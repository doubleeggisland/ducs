package com.ioiox.dei.duc.beans.model.master.employee;

import com.ioiox.dei.duc.beans.model.master.BaseRoleDelParam;
import com.ioiox.dei.duc.beans.model.master.BaseRoleDelParamBuilder;

public class EmployeeTmpSysResRoleDelParam
        extends BaseRoleDelParam {

    private EmployeeTmpSysResRoleDelParam(final Builder builder) {
        super(builder);
    }

    public static class Builder
            extends BaseRoleDelParamBuilder<EmployeeTmpSysResRoleDelParam> {

        @Override
        public EmployeeTmpSysResRoleDelParam build() {
            return new EmployeeTmpSysResRoleDelParam(this);
        }
    }
}
