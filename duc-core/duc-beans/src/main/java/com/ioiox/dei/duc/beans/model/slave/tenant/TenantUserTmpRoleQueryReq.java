package com.ioiox.dei.duc.beans.model.slave.tenant;

import com.ioiox.dei.duc.beans.model.slave.RoleQueryCfg;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TenantUserTmpRoleQueryReq {
    private TenantUserTmpRoleQueryParam queryParam;
    private RoleQueryCfg queryCfg;

    public TenantUserTmpRoleQueryReq(final TenantUserTmpRoleQueryParam queryParam,
                                     final RoleQueryCfg queryCfg) {
        this.queryParam = queryParam;
        this.queryCfg = queryCfg;
    }
}
