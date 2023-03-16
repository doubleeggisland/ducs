package com.ioiox.dei.duc.beans.vo.std.slave.tenant;

import com.ioiox.dei.core.vo.SlaveStdDataVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class TenantSlaveStdVO extends SlaveStdDataVO {
    /**
     * 租户编号
     */
    private String code;
    /**
     * 租户名称
     */
    private String name;
    /**
     * 备注
     */
    private String memo;
    /**
     * 租户状态
     * @see com.ioiox.dei.core.beans.DeiStatus
     */
    private String status;
    /**
     * 租户层级
     */
    private Integer lvl;
    /**
     * 父租户主键ID
     */
    private Long pid;
    /**
     * 归属的顶级租户ID
     */
    private Long rid;
    /**
     * 子租户
     */
    private List<TenantSlaveStdVO> children;
    /**
     * 父租户
     */
    private TenantSlaveStdVO parent;
}
