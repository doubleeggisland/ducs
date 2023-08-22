package com.ioiox.dei.duc.db.service.slave.employee;

import com.ioiox.dei.core.orm.mybatis.service.db.IBaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.EmployeeR2SysResRole;

import java.util.List;
import java.util.Map;

public interface EmployeeR2TmpSysResRoleSlaveDbSvc
        extends IBaseDeiSlaveDbService<EmployeeR2SysResRole> {

    Map<Long, List<Long>> getGroupedTmpSysResRoleIds(final List<Long> employeeIds);
    Map<Long, List<Long>> getGroupedEmployeeIds(final List<Long> tmpSysResRoleIds);
}
