package com.ioiox.dei.duc.beans.vo.std.slave.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ioiox.dei.duc.beans.vo.std.slave.UserSlaveVO;
import com.ioiox.dei.duc.beans.vo.std.slave.tenant.TenantSlaveVO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserAcctSlaveVO
        extends UserSlaveVO<UserAcctRoleSlaveVO, UserAcctSysResRoleSlaveVO, UserAcctTmpRoleSlaveVO, UserAcctTmpSysResRoleSlaveVO, AcctUserGrpSlaveVO> {
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
