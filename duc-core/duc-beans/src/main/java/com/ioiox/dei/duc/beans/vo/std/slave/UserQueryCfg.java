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
public class UserQueryCfg extends StdDataQueryCfg {
    private String needUserGrp;
    private String needSysPrjPrivileges;
    private String needRoles;
    private String needSysResRoles;
    private String needTmpRoles;
    private String needTmpSysResRoles;
    private UserGrpQueryCfg userGrpQueryCfg;
    private UserSysPrjPrivilegeQueryCfg sysPrjPrivilegeQueryCfg;
    private RoleQueryCfg roleQueryCfg;
    private SysResRoleQueryCfg sysResRoleQueryCfg;
    private RoleQueryCfg tmpRoleQueryCfg;
    private SysResRoleQueryCfg tmpSysResRoleQueryCfg;


    private UserQueryCfg(final Builder builder) {
        super(builder);
        needUserGrp = builder.needUserGrp;
        needSysPrjPrivileges = builder.needSysPrjPrivileges;
        needRoles = builder.needRoles;
        needSysResRoles = builder.needSysResRoles;
        needTmpRoles = builder.needTmpRoles;
        needTmpSysResRoles = builder.needTmpSysResRoles;
        userGrpQueryCfg = builder.userGrpQueryCfg;
        sysPrjPrivilegeQueryCfg = builder.sysPrjPrivilegeQueryCfg;
        roleQueryCfg = builder.roleQueryCfg;
        sysResRoleQueryCfg = builder.sysResRoleQueryCfg;
        tmpRoleQueryCfg = builder.tmpRoleQueryCfg;
        tmpSysResRoleQueryCfg = builder.tmpSysResRoleQueryCfg;
    }

    public static class Builder
            extends StdDataQueryCfgBuilder<UserQueryCfg> {
        private String needUserGrp;
        private String needSysPrjPrivileges;
        private String needRoles;
        private String needSysResRoles;
        private String needTmpRoles;
        private String needTmpSysResRoles;
        private UserGrpQueryCfg userGrpQueryCfg;
        private UserSysPrjPrivilegeQueryCfg sysPrjPrivilegeQueryCfg;
        private RoleQueryCfg roleQueryCfg;
        private SysResRoleQueryCfg sysResRoleQueryCfg;
        private RoleQueryCfg tmpRoleQueryCfg;
        private SysResRoleQueryCfg tmpSysResRoleQueryCfg;

        public Builder needUserGrp(final String needUserGrp) {
            this.needUserGrp = needUserGrp;
            return this;
        }
        public Builder needSysPrjPrivileges(final String needSysPrjPrivileges) {
            this.needSysPrjPrivileges = needSysPrjPrivileges;
            return this;
        }
        public Builder needRoles(final String needRoles) {
            this.needRoles = needRoles;
            return this;
        }
        public Builder needSysResRoles(final String needSysResRoles) {
            this.needSysResRoles = needSysResRoles;
            return this;
        }
        public Builder needTmpRoles(final String needTmpRoles) {
            this.needTmpRoles = needTmpRoles;
            return this;
        }
        public Builder needTmpSysResRoles(final String needTmpSysResRoles) {
            this.needTmpSysResRoles = needTmpSysResRoles;
            return this;
        }
        public Builder userGrpQueryCfg(final UserGrpQueryCfg userGrpQueryCfg) {
            this.userGrpQueryCfg = userGrpQueryCfg;
            return this;
        }
        public Builder sysPrjPrivilegeQueryCfg(final UserSysPrjPrivilegeQueryCfg sysPrjPrivilegeQueryCfg) {
            this.sysPrjPrivilegeQueryCfg = sysPrjPrivilegeQueryCfg;
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
        public Builder tmpRoleQueryCfg(final RoleQueryCfg tmpRoleQueryCfg) {
            this.tmpRoleQueryCfg = tmpRoleQueryCfg;
            return this;
        }
        public Builder tmpSysResRoleQueryCfg(final SysResRoleQueryCfg tmpSysResRoleQueryCfg) {
            this.tmpSysResRoleQueryCfg = tmpSysResRoleQueryCfg;
            return this;
        }

        @Override
        public UserQueryCfg build() {
            return new UserQueryCfg(this);
        }
    }
}
