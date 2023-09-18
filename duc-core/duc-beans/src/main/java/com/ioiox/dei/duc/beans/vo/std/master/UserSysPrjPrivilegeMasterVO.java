package com.ioiox.dei.duc.beans.vo.std.master;

import com.ioiox.dei.core.orm.mybatis.model.std.data.MasterStdDataVO;
import com.ioiox.dei.duc.beans.entity.UserSysPrjPrivilege;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserSysPrjPrivilegeMasterVO extends MasterStdDataVO {
    private Long userId;
    private Long sysPrjId;
    /**
     * 访问条件
     * @see com.ioiox.dei.duc.spring.core.model.SysPrjAccessCondition
     */
    private String accessCondition;

    public String uniqueKeyDigest() {
        return new UserSysPrjPrivilege.UniqueKey(userId, sysPrjId).toString();
    }
}
