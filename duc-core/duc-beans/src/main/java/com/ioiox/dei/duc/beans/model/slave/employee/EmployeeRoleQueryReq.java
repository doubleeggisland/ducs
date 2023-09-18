package com.ioiox.dei.duc.beans.model.slave.employee;

import com.ioiox.dei.duc.beans.model.slave.RoleQueryCfg;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeRoleQueryReq {
    private EmployeeRoleQueryParam queryParam;
    private RoleQueryCfg queryCfg;

    public EmployeeRoleQueryReq(final EmployeeRoleQueryParam queryParam,
                                final RoleQueryCfg queryCfg) {
        this.queryParam = queryParam;
        this.queryCfg = queryCfg;
    }
}
