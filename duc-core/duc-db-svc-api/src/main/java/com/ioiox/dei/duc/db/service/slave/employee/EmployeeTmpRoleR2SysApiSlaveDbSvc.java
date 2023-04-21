package com.ioiox.dei.duc.db.service.slave.employee;

import com.ioiox.dei.core.orm.mybatis.service.IBaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.RoleR2SysApi;

import java.util.List;
import java.util.Map;

public interface EmployeeTmpRoleR2SysApiSlaveDbSvc
        extends IBaseDeiSlaveDbService<RoleR2SysApi> {
    Map<Long, List<Long>> getGroupedSysApiSids(final List<Long> tmpRoleSids);
    Map<Long, List<Long>> getGroupedTmpRoleSids(final List<Long> sysApiSids);
}
