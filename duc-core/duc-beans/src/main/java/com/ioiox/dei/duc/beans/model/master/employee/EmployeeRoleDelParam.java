package com.ioiox.dei.duc.beans.model.master.employee;

import com.ioiox.dei.duc.beans.model.master.BaseRoleDelParam;
import com.ioiox.dei.duc.beans.model.master.BaseRoleDelParamBuilder;

public class EmployeeRoleDelParam
        extends BaseRoleDelParam {

    private EmployeeRoleDelParam(final Builder builder) {
        super(builder);
    }

    public static class Builder
            extends BaseRoleDelParamBuilder<EmployeeRoleDelParam> {

        @Override
        public EmployeeRoleDelParam build() {
            return new EmployeeRoleDelParam(this);
        }
    }
}
