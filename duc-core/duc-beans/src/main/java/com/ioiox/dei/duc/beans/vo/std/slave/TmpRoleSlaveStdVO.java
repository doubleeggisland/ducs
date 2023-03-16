package com.ioiox.dei.duc.beans.vo.std.slave;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public abstract class TmpRoleSlaveStdVO extends BaseTmpRoleSlaveStdVO {
    /**
     * 所属项目
     */
    @JsonIgnore
    private SysPrjSlaveStdVO sysPrj;
    /**
     * 分配的菜单
     */
    private List<MenuSlaveStdVO> menus;
    /**
     * 分配的系统接口
     */
    private List<SysApiSlaveStdVO> sysApis;
}
