package com.ioiox.dei.duc.beans.model.slave.user;

import com.ioiox.dei.duc.beans.model.slave.SysResRoleQueryCfg;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserAcctTmpSysResRoleQueryReq {
    private UserAcctTmpSysResRoleQueryParam queryParam;
    private SysResRoleQueryCfg queryCfg;

    public UserAcctTmpSysResRoleQueryReq(final UserAcctTmpSysResRoleQueryParam queryParam,
                                         final SysResRoleQueryCfg queryCfg) {
        this.queryParam = queryParam;
        this.queryCfg = queryCfg;
    }
}
