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
public class SysResRoleQueryCfg extends StdDataQueryCfg {
    private String needSysResources;
    private StdDataQueryCfg sysResQueryCfg;

    public SysResRoleQueryCfg(final Builder builder) {
        super(builder);
        needSysResources = builder.needSysResources;
        sysResQueryCfg = builder.sysResQueryCfg;
    }

    public static class Builder
            extends StdDataQueryCfgBuilder<SysResRoleQueryCfg> {
        private String needSysResources;
        private StdDataQueryCfg sysResQueryCfg;

        public Builder needSysResources(final String needSysResources) {
            this.needSysResources = needSysResources;
            return this;
        }
        public Builder sysResQueryCfg(final StdDataQueryCfg sysResQueryCfg) {
            this.sysResQueryCfg = sysResQueryCfg;
            return this;
        }

        @Override
        public SysResRoleQueryCfg build() {
            return new SysResRoleQueryCfg(this);
        }
    }
}
