package com.ioiox.dei.duc.beans.vo.std.slave;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public abstract class BaseSysResRoleSlaveVO extends SimpleRoleSlaveVO {
    /**
     * 分配的系统资源
     */
    private List<SysResSlaveVO> sysResources;
    /**
     * 所属项目
     */
    @JsonIgnore
    private SysPrjSlaveStdVO sysPrj;
}
