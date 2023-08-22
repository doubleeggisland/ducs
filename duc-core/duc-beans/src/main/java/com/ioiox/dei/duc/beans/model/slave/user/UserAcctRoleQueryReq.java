package com.ioiox.dei.duc.beans.model.slave.user;

import com.ioiox.dei.duc.beans.model.slave.RoleQueryCfg;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserAcctRoleQueryReq {
    private UserAcctRoleQueryParam queryParam;
    private RoleQueryCfg queryCfg;

    public UserAcctRoleQueryReq(final UserAcctRoleQueryParam queryParam,
                                final RoleQueryCfg queryCfg) {
        this.queryParam = queryParam;
        this.queryCfg = queryCfg;
    }
}
