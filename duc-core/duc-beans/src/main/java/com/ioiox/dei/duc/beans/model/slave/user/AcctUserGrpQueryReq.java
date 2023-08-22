package com.ioiox.dei.duc.beans.model.slave.user;

import com.ioiox.dei.duc.beans.model.slave.UserGrpQueryCfg;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AcctUserGrpQueryReq {
    private AcctUserGrpQueryParam queryParam;
    private UserGrpQueryCfg queryCfg;

    public AcctUserGrpQueryReq(final AcctUserGrpQueryParam queryParam,
                               final UserGrpQueryCfg queryCfg) {
        this.queryParam = queryParam;
        this.queryCfg = queryCfg;
    }
}
