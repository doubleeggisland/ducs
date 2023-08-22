package com.ioiox.dei.duc.beans.model.slave;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MenuQueryReq {
    private MenuQueryParam queryParam;
    private MenuQueryCfg queryCfg;

    public MenuQueryReq(final MenuQueryParam queryParam,
                        final MenuQueryCfg queryCfg) {
        this.queryParam = queryParam;
        this.queryCfg = queryCfg;
    }
}
