package com.ioiox.dei.duc.beans.vo.std.slave;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public abstract class UserGrpSlaveVO<R extends RoleSlaveVO, RR extends SysResRoleSlaveVO>
        extends BaseUserGrpSlaveVO {
    /**
     * 给用户组分配的角色
     */
    private List<R> roles;
    /**
     * 给用户组分配的资源角色
     */
    private List<RR> sysResRoles;
}
