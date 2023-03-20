package com.ioiox.dei.duc.beans.vo.std.slave;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
import com.ioiox.dei.core.vo.StdDataQueryParam;
import com.ioiox.dei.core.vo.StdDataQueryParamBuilder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysPrjQueryParam
        extends StdDataQueryParam {
    private List<String> codes;
    private List<String> statuses;

    private SysPrjQueryParam(final Builder builder) {
        super(builder);
        codes = builder.codes;
        statuses = builder.statuses;
    }

    @Override
    public Map<String, Object> queryParams() {
        final Map<String, Object> queryParams = super.queryParams();
        if (DeiCollectionUtil.isNotEmpty(this.codes)) {
            if (this.codes.size() > 1) {
                queryParams.put("codes", this.codes);
            } else {
                queryParams.put("code", this.codes.get(0));
            }
        }
        if (DeiCollectionUtil.isNotEmpty(this.statuses)) {
            if (this.statuses.size() > 1) {
                queryParams.put("statuses", this.statuses);
            } else {
                queryParams.put("status", this.statuses.get(0));
            }
        }
        return queryParams;
    }

    public static class Builder
            extends StdDataQueryParamBuilder<SysPrjQueryParam> {
        private List<String> codes;
        private List<String> statuses;

        public Builder codes(final List<String> codes) {
            this.codes = codes;
            return this;
        }
        public Builder statuses(final List<String> statuses) {
            this.statuses = statuses;
            return this;
        }

        @Override
        public SysPrjQueryParam build() {
            return new SysPrjQueryParam(this);
        }
    }
}
