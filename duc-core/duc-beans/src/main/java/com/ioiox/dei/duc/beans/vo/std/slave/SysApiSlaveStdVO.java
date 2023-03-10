package com.ioiox.dei.duc.beans.vo.std.slave;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ioiox.dei.core.vo.SlaveStdDataVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SysApiSlaveStdVO extends SlaveStdDataVO {
    /**
     * 系统接口编号
     */
    private String code;
    /**
     * 系统接口名称
     */
    private String name;
    /**
     * 备注
     */
    private String memo;
    /**
     * 系统接口地址
     */
    private String url;
    /**
     * HTTP请求方式
     */
    private String httpMethod;
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
     * 系统接口状态
     */
    private String status;

    /**
     * 所属项目
     */
    @JsonIgnore
    private SysPrjSlaveStdVO sysPrj;
}
