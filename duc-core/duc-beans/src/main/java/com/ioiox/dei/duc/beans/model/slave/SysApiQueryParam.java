package com.ioiox.dei.duc.beans.model.slave;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ioiox.dei.core.orm.mybatis.model.std.data.StdDataQueryParam;
import com.ioiox.dei.core.orm.mybatis.model.std.data.StdDataQueryParamBuilder;
import com.ioiox.dei.core.utils.DeiCollectionUtil;
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
public class SysApiQueryParam
        extends StdDataQueryParam {
    private List<String> codes;
    private List<Long> sysPrjIds;
    private List<String> types;
    private List<String> statuses;

    private SysApiQueryParam(final Builder builder) {
        super(builder);
        codes = builder.codes;
        sysPrjIds = builder.sysPrjIds;
        types = builder.types;
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
        if (DeiCollectionUtil.isNotEmpty(this.sysPrjIds)) {
            if (this.sysPrjIds.size() > 1) {
                queryParams.put("sysPrjSids", this.sysPrjIds);
            } else {
                queryParams.put("sysPrjSid", this.sysPrjIds.get(0));
            }
        }
        if (DeiCollectionUtil.isNotEmpty(this.types)) {
            if (this.types.size() > 1) {
                queryParams.put("types", this.types);
            } else {
                queryParams.put("type", this.types.get(0));
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
            extends StdDataQueryParamBuilder<SysApiQueryParam> {
        private List<String> codes;
        private List<Long> sysPrjIds;
        private List<String> types;
        private List<String> statuses;

        public Builder codes(final List<String> codes) {
            this.codes = codes;
            return this;
        }
        public Builder sysPrjIds(final List<Long> sysPrjIds) {
            this.sysPrjIds = sysPrjIds;
            return this;
        }
        public Builder types(final List<String> types) {
            this.types = types;
            return this;
        }
        public Builder statuses(final List<String> statuses) {
            this.statuses = statuses;
            return this;
        }

        @Override
        public SysApiQueryParam build() {
            return new SysApiQueryParam(this);
        }
    }
}
