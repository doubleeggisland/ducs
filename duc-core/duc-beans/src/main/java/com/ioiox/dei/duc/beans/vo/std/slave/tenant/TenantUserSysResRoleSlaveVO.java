package com.ioiox.dei.duc.beans.vo.std.slave.tenant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ioiox.dei.duc.beans.vo.std.slave.BaseSysResRoleSlaveVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TenantUserSysResRoleSlaveVO
        extends BaseSysResRoleSlaveVO {

    /**
     * 所属租户ID
     */
    private Long tenantId;

    /**
     * 所属租户
     */
    @JsonIgnore
    private TenantSlaveVO tenant;
}
