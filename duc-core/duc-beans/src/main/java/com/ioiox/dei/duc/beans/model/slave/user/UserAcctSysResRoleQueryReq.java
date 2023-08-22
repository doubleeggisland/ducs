package com.ioiox.dei.duc.beans.model.slave.user;

import com.ioiox.dei.duc.beans.model.slave.SysResRoleQueryCfg;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserAcctSysResRoleQueryReq {
    private UserAcctSysResRoleQueryParam queryParam;
    private SysResRoleQueryCfg queryCfg;

    public UserAcctSysResRoleQueryReq(final UserAcctSysResRoleQueryParam queryParam,
                                      final SysResRoleQueryCfg queryCfg) {
        this.queryParam = queryParam;
        this.queryCfg = queryCfg;
    }
}
