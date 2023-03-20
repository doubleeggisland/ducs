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
public class MenuQueryCfg extends StdDataQueryCfg {
    private String needSysApiMappings;
    private MenuSysApiMappingQueryCfg sysApiMappingQueryCfg;

    private MenuQueryCfg(final Builder builder) {
        needSysApiMappings = builder.needSysApiMappings;
        sysApiMappingQueryCfg = builder.sysApiMappingQueryCfg;
    }

    public static class Builder
            extends StdDataQueryCfgBuilder<MenuQueryCfg> {
        private String needSysApiMappings;
        private MenuSysApiMappingQueryCfg sysApiMappingQueryCfg;

        public Builder needSysApiMappings(final String needSysApiMappings) {
            this.needSysApiMappings = needSysApiMappings;
            return this;
        }
        public Builder sysApiMappingQueryCfg(final MenuSysApiMappingQueryCfg sysApiMappingQueryCfg) {
            this.sysApiMappingQueryCfg = sysApiMappingQueryCfg;
            return this;
        }

        @Override
        public MenuQueryCfg build() {
            return new MenuQueryCfg(this);
        }
    }
}
