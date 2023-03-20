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
public class UserGrpQueryCfg extends StdDataQueryCfg {
    private String needRoles;
    private String needSysResRoles;
    private RoleQueryCfg roleQueryCfg;
    private SysResRoleQueryCfg sysResRoleQueryCfg;

    private UserGrpQueryCfg(final Builder builder) {
        super(builder);
        needRoles = builder.needRoles;
        needSysResRoles = builder.needSysResRoles;
        roleQueryCfg = builder.roleQueryCfg;
        sysResRoleQueryCfg = builder.sysResRoleQueryCfg;
    }

    public static class Builder
            extends StdDataQueryCfgBuilder<UserGrpQueryCfg> {
        private String needRoles;
        private String needSysResRoles;
        private RoleQueryCfg roleQueryCfg;
        private SysResRoleQueryCfg sysResRoleQueryCfg;

        public Builder needRoles(final String needRoles) {
            this.needRoles = needRoles;
            return this;
        }
        public Builder needSysResRoles(final String needSysResRoles) {
            this.needSysResRoles = needSysResRoles;
            return this;
        }
        public Builder roleQueryCfg(final RoleQueryCfg roleQueryCfg) {
            this.roleQueryCfg = roleQueryCfg;
            return this;
        }
        public Builder sysResRoleQueryCfg(final SysResRoleQueryCfg sysResRoleQueryCfg) {
            this.sysResRoleQueryCfg = sysResRoleQueryCfg;
            return this;
        }

        @Override
        public UserGrpQueryCfg build() {
            return new UserGrpQueryCfg(this);
        }
    }
}
