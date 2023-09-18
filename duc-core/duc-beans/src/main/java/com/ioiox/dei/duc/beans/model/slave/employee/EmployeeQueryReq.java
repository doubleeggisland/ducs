package com.ioiox.dei.duc.beans.model.slave.employee;

import com.ioiox.dei.duc.beans.model.slave.UserQueryCfg;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeQueryReq {
    private EmployeeQueryParam queryParam;
    private UserQueryCfg queryCfg;

    public EmployeeQueryReq(final EmployeeQueryParam queryParam, final UserQueryCfg queryCfg) {
        this.queryParam = queryParam;
        this.queryCfg = queryCfg;
    }
}
