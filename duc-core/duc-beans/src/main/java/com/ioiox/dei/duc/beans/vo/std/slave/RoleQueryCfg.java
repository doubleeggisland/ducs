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
public class RoleQueryCfg extends StdDataQueryCfg {
    private String needMenus;
    private String needSysApiMappings;
    private MenuQueryCfg menuQueryCfg;
    private MenuSysApiMappingQueryCfg sysApiMappingQueryCfg;

    private RoleQueryCfg(final Builder builder) {
        super(builder);
        needMenus = builder.needMenus;
        needSysApiMappings = builder.needSysApiMappings;
        menuQueryCfg = builder.menuQueryCfg;
        sysApiMappingQueryCfg = builder.sysApiMappingQueryCfg;
    }

    public static class Builder
            extends StdDataQueryCfgBuilder<RoleQueryCfg> {
        private String needMenus;
        private String needSysApiMappings;
        private MenuQueryCfg menuQueryCfg;
        private MenuSysApiMappingQueryCfg sysApiMappingQueryCfg;

        public Builder needMenus(final String needMenus) {
            this.needMenus = needMenus;
            return this;
        }
        public Builder needSysApiMappings(final String needSysApiMappings) {
            this.needSysApiMappings = needSysApiMappings;
            return this;
        }
        public Builder menuQueryCfg(final MenuQueryCfg menuQueryCfg) {
            this.menuQueryCfg = menuQueryCfg;
            return this;
        }
        public Builder sysApiMappingQueryCfg(final MenuSysApiMappingQueryCfg sysApiMappingQueryCfg) {
            this.sysApiMappingQueryCfg = sysApiMappingQueryCfg;
            return this;
        }

        @Override
        public RoleQueryCfg build() {
            return new RoleQueryCfg(this);
        }
    }
}