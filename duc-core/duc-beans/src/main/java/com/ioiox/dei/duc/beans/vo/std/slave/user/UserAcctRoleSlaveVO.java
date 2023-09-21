package com.ioiox.dei.duc.beans.vo.std.slave.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ioiox.dei.duc.beans.vo.std.slave.BaseRoleSlaveVO;
import com.ioiox.dei.duc.beans.vo.std.slave.tenant.TenantSlaveVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserAcctRoleSlaveVO extends BaseRoleSlaveVO {
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
    private TenantSlaveVO tenant;
}
