package com.ioiox.dei.duc.beans.model.slave.tenant;

import com.ioiox.dei.duc.beans.model.slave.UserGrpQueryCfg;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TenantUserGrpQueryReq {
    private TenantUserGrpQueryParam queryParam;
    private UserGrpQueryCfg queryCfg;

    public TenantUserGrpQueryReq(final TenantUserGrpQueryParam queryParam,
                                 final UserGrpQueryCfg queryCfg) {
        this.queryParam = queryParam;
        this.queryCfg = queryCfg;
    }
}
