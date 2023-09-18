package com.ioiox.dei.duc.beans.model.slave.tenant;

import com.ioiox.dei.duc.beans.model.slave.SysResRoleQueryCfg;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TenantUserTmpSysResRoleQueryReq {
    private TenantUserTmpSysResRoleQueryParam queryParam;
    private SysResRoleQueryCfg queryCfg;

    public TenantUserTmpSysResRoleQueryReq(final TenantUserTmpSysResRoleQueryParam queryParam,
                                           final SysResRoleQueryCfg queryCfg) {
        this.queryParam = queryParam;
        this.queryCfg = queryCfg;
    }
}
