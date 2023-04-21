package com.ioiox.dei.duc.beans.vo.std.master;

import com.ioiox.dei.core.vo.MasterStdDataVO;
import com.ioiox.dei.duc.beans.entity.UserSysPrjPrivilege;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserSysPrjPrivilegeMasterStdVO extends MasterStdDataVO {
    private Long userId;
    private Long sysPrjId;
    private String accessCondition;

    public String uniqueKeyDigest() {
        return new UserSysPrjPrivilege.UniqueKey(userId, sysPrjId).toString();
    }
}
