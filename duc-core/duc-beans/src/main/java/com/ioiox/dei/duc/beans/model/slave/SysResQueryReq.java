package com.ioiox.dei.duc.beans.model.slave;

import com.ioiox.dei.core.orm.mybatis.model.std.data.StdDataQueryCfg;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SysResQueryReq {
    private SysResQueryParam queryParam;
    private StdDataQueryCfg queryCfg;

    public SysResQueryReq(final SysResQueryParam queryParam,
                          final StdDataQueryCfg queryCfg) {
        this.queryParam = queryParam;
        this.queryCfg = queryCfg;
    }
}
