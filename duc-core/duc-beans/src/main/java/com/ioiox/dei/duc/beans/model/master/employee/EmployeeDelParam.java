package com.ioiox.dei.duc.beans.model.master.employee;

import com.ioiox.dei.duc.beans.model.master.UserDelParam;
import com.ioiox.dei.duc.beans.model.master.UserDelParamBuilder;

public class EmployeeDelParam extends UserDelParam {

    private EmployeeDelParam(final Builder builder) {
        super(builder);
    }

    public static class Builder
            extends UserDelParamBuilder<EmployeeDelParam> {

        @Override
        public EmployeeDelParam build() {
            return new EmployeeDelParam(this);
        }
    }
}
