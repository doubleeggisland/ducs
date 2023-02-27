package com.ioiox.dei.duc.beans.vo.std.master.user;

import com.ioiox.dei.duc.beans.vo.std.master.TmpRoleMasterStdVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserAcctTmpRoleMasterStdVO extends TmpRoleMasterStdVO {
    /**
     * 所属租户ID
     */
    private Long tenantId;
    /**
     * 所属企业ID
     */
    private Long corpId;
}
