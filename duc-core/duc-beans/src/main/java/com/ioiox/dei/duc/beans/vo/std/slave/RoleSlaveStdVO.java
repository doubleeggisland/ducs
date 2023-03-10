package com.ioiox.dei.duc.beans.vo.std.slave;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public abstract class RoleSlaveStdVO
        extends BaseRoleSlaveStdVO {

    /**
     * 分配的菜单
     */
    private List<MenuSlaveStdVO> menus;
    /**
     * 分配的系统接口
     */
    private List<SysApiSlaveStdVO> sysApis;
}
