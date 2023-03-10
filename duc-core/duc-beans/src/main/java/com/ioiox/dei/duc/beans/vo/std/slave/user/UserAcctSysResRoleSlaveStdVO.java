package com.ioiox.dei.duc.beans.vo.std.slave.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ioiox.dei.duc.beans.vo.std.slave.SysResRoleSlaveStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.tenant.TenantSlaveStdVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserAcctSysResRoleSlaveStdVO
        extends SysResRoleSlaveStdVO {
    /**
     * 所属租户ID
     */
    private Long tenantId;
    /**
     * 所属企业ID
     */
    private Long corpId;
    /**
     * 所属租户
     */
    @JsonIgnore
    private TenantSlaveStdVO tenant;
}
