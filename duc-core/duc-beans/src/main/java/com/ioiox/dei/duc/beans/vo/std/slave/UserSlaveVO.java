package com.ioiox.dei.duc.beans.vo.std.slave;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public abstract class UserSlaveVO<
        R extends BaseRoleSlaveVO,
        RR extends BaseSysResRoleSlaveVO,
        TR extends BaseRoleSlaveVO,
        TRR extends BaseSysResRoleSlaveVO,
        UG extends UserGrpSlaveVO<R, RR>>
        extends BaseUserSlaveVO {
    /**
     * 分配的用户组
     */
    private List<UG> userGrps;
    /**
     * 分配的项目权限
     */
    private List<UserSysPrjPrivilegeSlaveVO> sysPrjPrivileges;
    /**
     * 分配的角色
     */
    private List<R> roles;
    /**
     * 分配的系统资源角色
     */
    private List<RR> sysResRoles;
    /**
     * 分配的临时角色
     */
    private List<TR> tmpRoles;
    /**
     * 分配的临时系统资源角色
     */
    private List<TRR> tmpSysResRoles;
}
