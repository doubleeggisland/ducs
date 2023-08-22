package com.ioiox.dei.duc.beans.model.slave;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ioiox.dei.core.orm.mybatis.model.std.data.StdDataQueryCfg;
import com.ioiox.dei.core.orm.mybatis.model.std.data.StdDataQueryCfgBuilder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysPrjQueryCfg extends StdDataQueryCfg {
    private String needMenus;
    private String needSysApis;
    private String needSysResources;
    private MenuQueryCfg menuQueryCfg;
    private StdDataQueryCfg sysApiQueryCfg;
    private StdDataQueryCfg sysResQueryCfg;

    private SysPrjQueryCfg(final Builder builder) {
        super(builder);
        needMenus = builder.needMenus;
        needSysApis = builder.needSysApis;
        needSysResources = builder.needSysResources;
        menuQueryCfg = builder.menuQueryCfg;
        sysApiQueryCfg = builder.sysApiQueryCfg;
        sysResQueryCfg = builder.sysResQueryCfg;
    }

    public static class Builder
            extends StdDataQueryCfgBuilder<SysPrjQueryCfg> {
        private String needMenus;
        private String needSysApis;
        private String needSysResources;
        private MenuQueryCfg menuQueryCfg;
        private StdDataQueryCfg sysApiQueryCfg;
        private StdDataQueryCfg sysResQueryCfg;

        public Builder needMenus(final String needMenus) {
            this.needMenus = needMenus;
            return this;
        }
        public Builder needSysApis(final String needSysApis) {
            this.needSysApis = needSysApis;
            return this;
        }
        public Builder needSysResources(final String needSysResources) {
            this.needSysResources = needSysResources;
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
        public Builder sysResQueryCfg(final StdDataQueryCfg sysResQueryCfg) {
            this.sysResQueryCfg = sysResQueryCfg;
            return this;
        }

        @Override
        public SysPrjQueryCfg build() {
            return new SysPrjQueryCfg(this);
        }
    }
}
