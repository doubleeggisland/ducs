package com.ioiox.dei.duc.beans.model.slave.tenant;

import com.ioiox.dei.duc.beans.model.slave.SysResRoleQueryCfg;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TenantUserSysResRoleQueryReq {
    private TenantUserSysResRoleQueryParam queryParam;
    private SysResRoleQueryCfg queryCfg;

    public TenantUserSysResRoleQueryReq(final TenantUserSysResRoleQueryParam queryParam,
                                        final SysResRoleQueryCfg queryCfg) {
        this.queryParam = queryParam;
        this.queryCfg = queryCfg;
    }
}
