package com.ioiox.dei.duc.beans.model.slave;

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
public class UserSysPrjPrivilegeQueryCfg extends StdDataQueryCfg {
    private String needSysPrj;
    private SysPrjQueryCfg sysPrjQueryCfg;

    private UserSysPrjPrivilegeQueryCfg(final Builder builder) {
        super(builder);
        needSysPrj = builder.needSysPrj;
        sysPrjQueryCfg = builder.sysPrjQueryCfg;
    }

    public static class Builder
            extends StdDataQueryCfgBuilder<UserSysPrjPrivilegeQueryCfg> {
        private String needSysPrj;
        private SysPrjQueryCfg sysPrjQueryCfg;

        public Builder needSysPrj(final String needSysPrj) {
            this.needSysPrj = needSysPrj;
            return this;
        }
        public Builder sysPrjQueryCfg(final SysPrjQueryCfg sysPrjQueryCfg) {
            this.sysPrjQueryCfg = sysPrjQueryCfg;
            return this;
        }

        @Override
        public UserSysPrjPrivilegeQueryCfg build() {
            return new UserSysPrjPrivilegeQueryCfg(this);
        }
    }
}
