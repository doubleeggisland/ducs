package com.ioiox.dei.duc.beans.model.master.employee;

import com.ioiox.dei.duc.beans.model.master.BaseRoleDelParam;
import com.ioiox.dei.duc.beans.model.master.BaseRoleDelParamBuilder;

public class EmployeeTmpRoleDelParam
        extends BaseRoleDelParam {

    private EmployeeTmpRoleDelParam(final Builder builder) {
        super(builder);
    }

    public static class Builder
            extends BaseRoleDelParamBuilder<EmployeeTmpRoleDelParam> {

        @Override
        public EmployeeTmpRoleDelParam build() {
            return new EmployeeTmpRoleDelParam(this);
        }
    }
}
