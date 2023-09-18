package com.ioiox.dei.duc.beans.model.slave.tenant;

import com.ioiox.dei.duc.beans.model.slave.UserQueryCfg;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TenantUserQueryReq {
    private TenantUserQueryParam queryParam;
    private UserQueryCfg queryCfg;

    public TenantUserQueryReq(final TenantUserQueryParam queryParam,
                              final UserQueryCfg queryCfg) {
        this.queryParam = queryParam;
        this.queryCfg = queryCfg;
    }
}
