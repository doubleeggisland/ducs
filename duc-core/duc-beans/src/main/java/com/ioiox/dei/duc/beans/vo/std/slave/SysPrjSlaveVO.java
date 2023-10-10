package com.ioiox.dei.duc.beans.vo.std.slave;

import com.ioiox.dei.core.orm.mybatis.model.std.data.SlaveStdDataVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SysPrjSlaveVO extends SlaveStdDataVO {
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
     * @see com.ioiox.dei.core.constant.DeiStatus
     */
    private String status;
    /**
     * 备注
     */
    private String memo;

    /**
     * 系统的菜单
     */
    private List<MenuSlaveVO> menus;

    /**
     * 系统的接口
     */
    private List<SysApiSlaveVO> sysApis;

    /**
     * 系统的资源
     */
    private List<SysResSlaveVO> sysResources;
}