package com.ioiox.dei.duc.beans.model.slave.tenant;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TenantQueryReq {
    private TenantQueryParam queryParam;
    private TenantQueryCfg queryCfg;

    public TenantQueryReq(final TenantQueryParam queryParam,
                          final TenantQueryCfg queryCfg) {
        this.queryParam = queryParam;
        this.queryCfg = queryCfg;
    }
}
