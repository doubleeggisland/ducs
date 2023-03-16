package com.ioiox.dei.duc.beans.vo.std.slave;

import com.ioiox.dei.core.vo.SlaveStdDataVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BaseRoleSlaveStdVO extends SlaveStdDataVO {
    /**
     * 角色编号
     */
    private String code;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 角色类型
     * @see com.ioiox.dei.duc.beans.constant.RoleType
     */
    private String type;
    /**
     * 状态
     * @see com.ioiox.dei.core.beans.DeiStatus
     */
    private String status;
    /**
     * 备注
     */
    private String memo;
    /**
     * 所属项目ID
     */
    private Long sysPrjId;
}
