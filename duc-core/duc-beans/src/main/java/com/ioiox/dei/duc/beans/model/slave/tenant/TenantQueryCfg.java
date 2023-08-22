package com.ioiox.dei.duc.beans.model.slave.tenant;

import com.ioiox.dei.core.orm.mybatis.model.std.data.StdDataQueryCfg;
import com.ioiox.dei.core.orm.mybatis.model.std.data.StdDataQueryCfgBuilder;
import com.ioiox.dei.duc.beans.model.slave.RoleQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.SysResRoleQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.UserGrpQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.UserQueryCfg;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TenantQueryCfg
        extends StdDataQueryCfg {
    private String needUserGrps;
    private String needTenantUsers;
    private String needRoles;
    private String needSysResRoles;
    private String needTmpRoles;
    private String needTmpSysResRoles;

    private UserGrpQueryCfg userGrpQueryCfg;
    private UserQueryCfg tenantUserQueryCfg;
    private RoleQueryCfg roleQueryCfg;
    private SysResRoleQueryCfg sysResRoleQueryCfg;
    private RoleQueryCfg tmpRoleQueryCfg;
    private SysResRoleQueryCfg tmpSysResRoleQueryCfg;

    private TenantQueryCfg(final Builder builder) {
        super(builder);
        needUserGrps = builder.needUserGrps;
        needTenantUsers = builder.needTenantUsers;
        needRoles = builder.needRoles;
        needSysResRoles = builder.needSysResRoles;
        needTmpRoles = builder.needTmpRoles;
        needTmpSysResRoles = builder.needTmpSysResRoles;
        userGrpQueryCfg = builder.userGrpQueryCfg;
        tenantUserQueryCfg = builder.tenantUserQueryCfg;
        roleQueryCfg = builder.roleQueryCfg;
        sysResRoleQueryCfg = builder.sysResRoleQueryCfg;
        tmpRoleQueryCfg = builder.tmpRoleQueryCfg;
        tmpSysResRoleQueryCfg = builder.tmpSysResRoleQueryCfg;
    }

    public static class Builder
            extends StdDataQueryCfgBuilder<TenantQueryCfg> {
        private String needUserGrps;
        private String needTenantUsers;
        private String needRoles;
        private String needSysResRoles;
        private String needTmpRoles;
        private String needTmpSysResRoles;

        private UserGrpQueryCfg userGrpQueryCfg;
        private UserQueryCfg tenantUserQueryCfg;
        private RoleQueryCfg roleQueryCfg;
        private SysResRoleQueryCfg sysResRoleQueryCfg;
        private RoleQueryCfg tmpRoleQueryCfg;
        private SysResRoleQueryCfg tmpSysResRoleQueryCfg;

        public Builder needUserGrps(final String needUserGrps) {
            this.needUserGrps = needUserGrps;
            return this;
        }
        public Builder needTenantUsers(final String needTenantUsers) {
            this.needTenantUsers = needTenantUsers;
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
        public Builder tenantUserQueryCfg(final UserQueryCfg tenantUserQueryCfg) {
            this.tenantUserQueryCfg = tenantUserQueryCfg;
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
        public TenantQueryCfg build() {
            return new TenantQueryCfg(this);
        }
    }
}
