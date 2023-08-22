package com.ioiox.dei.duc.beans.vo.std.slave;

import com.ioiox.dei.core.orm.mybatis.model.std.data.SlaveStdDataVO;
import com.ioiox.dei.duc.beans.entity.UserSysPrjPrivilege;
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
     * @see com.ioiox.dei.duc.spring.core.model.SysPrjAccessCondition
     */
    private String accessCondition;
    /**
     * 项目
     */
    private SysPrjSlaveStdVO sysPrj;

    public String uniqueKeyDigest() {
        return new UserSysPrjPrivilege.UniqueKey(userId, sysPrjId).toString();
    }
}
