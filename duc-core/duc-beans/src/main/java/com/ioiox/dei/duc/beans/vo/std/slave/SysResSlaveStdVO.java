package com.ioiox.dei.duc.beans.vo.std.slave;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ioiox.dei.core.vo.SlaveStdDataVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SysResSlaveStdVO extends SlaveStdDataVO {
    /**
     * 资源编号
     */
    private String code;
    /**
     * 资源名称
     */
    private String name;
    /**
     * 资源类型
     * @see com.ioiox.dei.duc.beans.entity.SysRes.Type
     */
    private String type;
    /**
     * 资源状态
     * @see com.ioiox.dei.core.beans.DeiStatus
     */
    private String status;
    /**
     * 备注
     */
    private String memo;
    /**
     * 所属系统模块名称
     */
    private String sysPrjModuleName;
    /**
     * 所属系统模块编号
     */
    private String sysPrjModuleCode;
    /**
     * 所属项目ID
     */
    private Long sysPrjId;
    /**
     * 所属项目
     */
    @JsonIgnore
    private SysPrjSlaveStdVO sysPrj;
}
