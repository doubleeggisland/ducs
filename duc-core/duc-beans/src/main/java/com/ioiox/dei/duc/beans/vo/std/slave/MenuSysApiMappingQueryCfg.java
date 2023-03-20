package com.ioiox.dei.duc.beans.vo.std.slave;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ioiox.dei.core.vo.StdDataQueryCfg;
import com.ioiox.dei.core.vo.StdDataQueryCfgBuilder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MenuSysApiMappingQueryCfg extends StdDataQueryCfg {
    private String needSysApi;
    private StdDataQueryCfg sysApiQueryCfg;

    private MenuSysApiMappingQueryCfg(final Builder builder) {
        super(builder);
        needSysApi = builder.needSysApi;
        sysApiQueryCfg = builder.sysApiQueryCfg;
    }

    public static class Builder
            extends StdDataQueryCfgBuilder<MenuSysApiMappingQueryCfg> {
        private String needSysApi;
        private StdDataQueryCfg sysApiQueryCfg;

        public Builder needSysApi(final String needSysApi) {
            this.needSysApi = needSysApi;
            return this;
        }
        public Builder sysApiQueryCfg(final StdDataQueryCfg sysApiQueryCfg) {
            this.sysApiQueryCfg = sysApiQueryCfg;
            return this;
        }

        @Override
        public MenuSysApiMappingQueryCfg build() {
            return new MenuSysApiMappingQueryCfg(this);
        }
    }
}
