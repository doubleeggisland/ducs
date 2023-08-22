package com.ioiox.dei.duc.db.service.slave.employee;

import com.ioiox.dei.core.orm.mybatis.service.db.IBaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.UserGrpR2SysResRole;

import java.util.List;
import java.util.Map;

public interface EmployeeUserGrpR2SysResRoleSlaveDbSvc
        extends IBaseDeiSlaveDbService<UserGrpR2SysResRole> {

    Map<Long, List<Long>> getGroupedSysResRoleIds(final List<Long> employeeUserGrpIds);
    Map<Long, List<Long>> getGroupedEmployeeUserGrpIds(final List<Long> sysResRoleIds);
}
