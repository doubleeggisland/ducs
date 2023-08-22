package com.ioiox.dei.duc.beans.model.slave.user;

import com.ioiox.dei.duc.beans.model.slave.RoleQueryCfg;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserAcctTmpRoleQueryReq {
    private UserAcctTmpRoleQueryParam queryParam;
    private RoleQueryCfg queryCfg;

    public UserAcctTmpRoleQueryReq(final UserAcctTmpRoleQueryParam queryParam,
                                   final RoleQueryCfg queryCfg) {
        this.queryParam = queryParam;
        this.queryCfg = queryCfg;
    }
}
