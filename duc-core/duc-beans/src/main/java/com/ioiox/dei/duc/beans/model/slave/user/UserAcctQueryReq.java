package com.ioiox.dei.duc.beans.model.slave.user;

import com.ioiox.dei.duc.beans.model.slave.UserQueryCfg;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserAcctQueryReq {
    private UserAcctQueryParam queryParam;
    private UserQueryCfg queryCfg;

    public UserAcctQueryReq(final UserAcctQueryParam queryParam,
                            final UserQueryCfg queryCfg) {
        this.queryParam = queryParam;
        this.queryCfg = queryCfg;
    }
}
