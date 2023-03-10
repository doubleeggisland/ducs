package com.ioiox.dei.duc.beans.vo.std.slave;

import com.ioiox.dei.core.vo.SlaveStdDataVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public abstract class UserGrpSlaveStdVO<R extends RoleSlaveStdVO, RR extends SysResRoleSlaveStdVO>
        extends SlaveStdDataVO {
    /**
     * 用户组编号
     */
    private String code;
    /**
     * 用户组名称
     */
    private String name;
    /**
     * 备注
     */
    private String memo;
    /**
     * 用户组状态
     */
    private String status;
    /**
     * 给用户组分配的角色
     */
    private List<R> roles;
    /**
     * 给用户组分配的资源角色
     */
    private List<RR> sysResRoles;
}
