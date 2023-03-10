package com.ioiox.dei.duc.beans.vo.std.slave;

import com.ioiox.dei.core.vo.SlaveStdDataVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SysPrjSlaveStdVO extends SlaveStdDataVO {
    /**
     * 系统编号
     */
    private String code;
    /**
     * 系统名称
     */
    private String name;
    /**
     * 系统状态
     * @see com.ioiox.dei.core.beans.DeiStatus
     */
    private String status;
    /**
     * 备注
     */
    private String memo;

    /**
     * 系统的菜单
     */
    private List<MenuSlaveStdVO> menus;

    /**
     * 系统的接口
     */
    private List<SysApiSlaveStdVO> sysApis;

    /**
     * 系统的资源
     */
    private List<SysResSlaveStdVO> sysResources;
}