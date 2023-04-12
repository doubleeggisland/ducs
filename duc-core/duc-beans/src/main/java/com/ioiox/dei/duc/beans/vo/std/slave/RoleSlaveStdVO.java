package com.ioiox.dei.duc.beans.vo.std.slave;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

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
     * 菜单与系统接口映射信息
     * key: menuId(菜单ID)
     */
    private Map<Long, List<MenuSysApiMappingSlaveStdVO>> sysApiMappings;
    /**
     * 分配的系统接口
     */
    private List<SysApiSlaveStdVO> sysApis;
    /**
     * 所属项目
     */
    @JsonIgnore
    private SysPrjSlaveStdVO sysPrj;
}
