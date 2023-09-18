package com.ioiox.dei.duc.beans.model.slave.employee;

import com.ioiox.dei.duc.beans.model.slave.SysResRoleQueryCfg;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeTmpSysResRoleQueryReq {
    private EmployeeTmpSysResRoleQueryParam queryParam;
    private SysResRoleQueryCfg queryCfg;

    public EmployeeTmpSysResRoleQueryReq(final EmployeeTmpSysResRoleQueryParam queryParam,
                                         final SysResRoleQueryCfg queryCfg) {
        this.queryParam = queryParam;
        this.queryCfg = queryCfg;
    }
}
