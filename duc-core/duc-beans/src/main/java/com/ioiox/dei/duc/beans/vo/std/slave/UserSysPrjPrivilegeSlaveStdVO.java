package com.ioiox.dei.duc.beans.vo.std.slave;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ioiox.dei.core.vo.SlaveStdDataVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserSysPrjPrivilegeSlaveStdVO
        extends SlaveStdDataVO {
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 系统ID
     */
    private Long sysPrjId;
    /**
     * 访问条件
     * @see com.ioiox.dei.duc.beans.entity.UserSysPrjPrivilege.AccessCondition
     */
    private String accessCondition;
    /**
     * 项目
     */
    private SysPrjSlaveStdVO sysPrj;
}
