package com.ioiox.dei.duc.beans.model.slave.employee;

import com.ioiox.dei.duc.beans.model.slave.RoleQueryCfg;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeTmpRoleQueryReq {

    private EmployeeTmpRoleQueryParam queryParam;
    private RoleQueryCfg queryCfg;

    public EmployeeTmpRoleQueryReq(final EmployeeTmpRoleQueryParam queryParam, final RoleQueryCfg queryCfg) {
        this.queryParam = queryParam;
        this.queryCfg = queryCfg;
    }
}
