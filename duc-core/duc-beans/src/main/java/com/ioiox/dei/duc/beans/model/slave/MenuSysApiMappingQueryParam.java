package com.ioiox.dei.duc.beans.model.slave;

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
public class MenuSysApiMappingQueryParam extends StdDataQueryParam {
    private List<Long> menuIds;
    private List<Long> sysApiIds;

    private MenuSysApiMappingQueryParam(final Builder builder) {
        super(builder);
        menuIds = builder.menuIds;
        sysApiIds = builder.sysApiIds;
    }

    @Override
    public Map<String, Object> queryParams() {
        final Map<String, Object> queryParams = super.queryParams();
        if (DeiCollectionUtil.isNotEmpty(this.menuIds)) {
            if (this.menuIds.size() > 1) {
                queryParams.put("menuSids", this.menuIds);
            } else {
                queryParams.put("menuSid", this.menuIds.get(0));
            }
        }
        if (DeiCollectionUtil.isNotEmpty(this.sysApiIds)) {
            if (this.sysApiIds.size() > 1) {
                queryParams.put("sysApiSids", this.sysApiIds);
            } else {
                queryParams.put("sysApiSid", this.sysApiIds.get(0));
            }
        }
        return queryParams;
    }

    public static class Builder
            extends StdDataQueryParamBuilder<MenuSysApiMappingQueryParam> {
        private List<Long> menuIds;
        private List<Long> sysApiIds;

        public Builder menuIds(final List<Long> menuIds) {
            this.menuIds = menuIds;
            return this;
        }
        public Builder sysApiIds(final List<Long> sysApiIds) {
            this.sysApiIds = sysApiIds;
            return this;
        }

        @Override
        public MenuSysApiMappingQueryParam build() {
            return new MenuSysApiMappingQueryParam(this);
        }
    }
}
