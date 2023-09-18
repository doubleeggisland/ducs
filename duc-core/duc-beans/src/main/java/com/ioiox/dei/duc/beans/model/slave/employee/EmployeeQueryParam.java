package com.ioiox.dei.duc.beans.model.slave.employee;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ioiox.dei.duc.beans.model.slave.UserQueryParam;
import com.ioiox.dei.duc.beans.model.slave.UserQueryParamBuilder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeQueryParam extends UserQueryParam {
    private String realNameLike;
    private String gender;

    private EmployeeQueryParam(final Builder builder) {
        super(builder);
        realNameLike = builder.realNameLike;
        gender = builder.gender;
    }

    @Override
    public Map<String, Object> queryParams() {
        final Map<String, Object> queryParams = super.queryParams();
        if (StringUtils.isNotBlank(realNameLike)) {
            queryParams.put("realNameLike", realNameLike);
        }
        if (StringUtils.isNotBlank(gender)) {
            queryParams.put("gender", gender);
        }
        return queryParams;
    }

    public static class Builder
            extends UserQueryParamBuilder<EmployeeQueryParam> {
        private String realNameLike;
        private String gender;

        public Builder realNameLike(final String realNameLike) {
            this.realNameLike = realNameLike;
            return this;
        }
        public Builder gender(final String gender) {
            this.gender = gender;
            return this;
        }

        @Override
        public EmployeeQueryParam build() {
            return new EmployeeQueryParam(this);
        }
    }
}
