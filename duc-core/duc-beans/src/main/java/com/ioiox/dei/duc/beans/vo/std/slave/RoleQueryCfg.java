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
    private String needSysApis;
    private MenuQueryCfg menuQueryCfg;
    private StdDataQueryCfg sysApiQueryCfg;

    private RoleQueryCfg(final Builder builder) {
        super(builder);
        needMenus = builder.needMenus;
        needSysApis = builder.needSysApis;
        menuQueryCfg = builder.menuQueryCfg;
        sysApiQueryCfg = builder.sysApiQueryCfg;
    }

    public static class Builder
            extends StdDataQueryCfgBuilder<RoleQueryCfg> {
        private String needMenus;
        private String needSysApis;
        private MenuQueryCfg menuQueryCfg;
        private StdDataQueryCfg sysApiQueryCfg;

        public Builder needMenus(final String needMenus) {
            this.needMenus = needMenus;
            return this;
        }
        public Builder needSysApis(final String needSysApis) {
            this.needSysApis = needSysApis;
            return this;
        }
        public Builder menuQueryCfg(final MenuQueryCfg menuQueryCfg) {
            this.menuQueryCfg = menuQueryCfg;
            return this;
        }
        public Builder sysApiQueryCfg(final StdDataQueryCfg sysApiQueryCfg) {
            this.sysApiQueryCfg = sysApiQueryCfg;
            return this;
        }

        @Override
        public RoleQueryCfg build() {
            return new RoleQueryCfg(this);
        }
    }
}
