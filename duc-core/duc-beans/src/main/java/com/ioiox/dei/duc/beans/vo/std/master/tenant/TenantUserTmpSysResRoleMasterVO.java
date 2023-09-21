package com.ioiox.dei.duc.beans.vo.std.master.tenant;

import com.ioiox.dei.duc.beans.vo.std.master.BaseSysResRoleMasterVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TenantUserTmpSysResRoleMasterVO
        extends BaseSysResRoleMasterVO {
    /**
     * 所属租户ID
     */
    private Long tenantId;
}
