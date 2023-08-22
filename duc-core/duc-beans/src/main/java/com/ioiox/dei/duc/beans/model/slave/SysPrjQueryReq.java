package com.ioiox.dei.duc.beans.model.slave;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SysPrjQueryReq {
    private SysPrjQueryParam queryParam;
    private SysPrjQueryCfg queryCfg;

    public SysPrjQueryReq(final SysPrjQueryParam queryParam,
                          final SysPrjQueryCfg queryCfg) {
        this.queryParam = queryParam;
        this.queryCfg = queryCfg;
    }
}
