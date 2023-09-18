package com.ioiox.dei.duc.beans.model.slave.employee;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ioiox.dei.duc.beans.model.slave.UserGrpQueryParam;
import com.ioiox.dei.duc.beans.model.slave.UserGrpQueryParamBuilder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeUserGrpQueryParam
        extends UserGrpQueryParam {

    private EmployeeUserGrpQueryParam(final Builder builder) {
        super(builder);
    }

    public static class Builder
            extends UserGrpQueryParamBuilder<EmployeeUserGrpQueryParam> {

        @Override
        public EmployeeUserGrpQueryParam build() {
            return new EmployeeUserGrpQueryParam(this);
        }
    }
}
