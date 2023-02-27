package com.ioiox.dei.duc.beans.vo.std.master.user;

import com.ioiox.dei.duc.beans.vo.std.master.TmpSysResRoleMasterStdVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserAcctTmpSysResRoleMasterStdVO
        extends TmpSysResRoleMasterStdVO {
    /**
     * 所属租户ID
     */
    private Long tenantId;
    /**
     * 所属企业ID
     */
    private Long corpId;
}
