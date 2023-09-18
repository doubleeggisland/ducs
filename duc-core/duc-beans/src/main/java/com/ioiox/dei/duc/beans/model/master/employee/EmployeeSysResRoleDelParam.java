package com.ioiox.dei.duc.beans.model.master.employee;

import com.ioiox.dei.duc.beans.model.master.BaseRoleDelParam;
import com.ioiox.dei.duc.beans.model.master.BaseRoleDelParamBuilder;

public class EmployeeSysResRoleDelParam
        extends BaseRoleDelParam {

    private EmployeeSysResRoleDelParam(final Builder builder) {
        super(builder);
    }

    public static class Builder
            extends BaseRoleDelParamBuilder<EmployeeSysResRoleDelParam> {

        @Override
        public EmployeeSysResRoleDelParam build() {
            return new EmployeeSysResRoleDelParam(this);
        }
    }
}
