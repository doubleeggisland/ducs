package com.ioiox.dei.duc.beans.vo.std.master.tenant;

import com.ioiox.dei.duc.beans.vo.std.master.TmpRoleMasterVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TenantUserTmpRoleMasterVO
        extends TmpRoleMasterVO {
    /**
     * 所属租户ID
     */
    private Long tenantId;
}
