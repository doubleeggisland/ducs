package com.ioiox.dei.duc.beans.model.master.employee;

import com.ioiox.dei.duc.beans.model.master.UserGrpDelParam;
import com.ioiox.dei.duc.beans.model.master.UserGrpDelParamBuilder;

public class EmployeeUserGrpDelParam extends UserGrpDelParam {

    private EmployeeUserGrpDelParam(final Builder builder) {
        super(builder);
    }

    public static class Builder
            extends UserGrpDelParamBuilder<EmployeeUserGrpDelParam> {

        @Override
        public EmployeeUserGrpDelParam build() {
            return new EmployeeUserGrpDelParam(this);
        }
    }
}
