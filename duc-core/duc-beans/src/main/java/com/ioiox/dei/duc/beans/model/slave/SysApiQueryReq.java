package com.ioiox.dei.duc.beans.model.slave;

import com.ioiox.dei.core.orm.mybatis.model.std.data.StdDataQueryCfg;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SysApiQueryReq {
    private SysApiQueryParam queryParam;
    private StdDataQueryCfg queryCfg;

    public SysApiQueryReq(final SysApiQueryParam queryParam,
                          final StdDataQueryCfg queryCfg) {
        this.queryParam = queryParam;
        this.queryCfg = queryCfg;
    }
}
