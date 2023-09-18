package com.ioiox.dei.duc.beans.model.slave.employee;

import com.ioiox.dei.duc.beans.model.slave.SysResRoleQueryCfg;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeSysResRoleQueryReq {

    private EmployeeSysResRoleQueryParam queryParam;
    private SysResRoleQueryCfg queryCfg;

    public EmployeeSysResRoleQueryReq(final EmployeeSysResRoleQueryParam queryParam,
                                      final SysResRoleQueryCfg queryCfg) {
        this.queryParam = queryParam;
        this.queryCfg = queryCfg;
    }
}
