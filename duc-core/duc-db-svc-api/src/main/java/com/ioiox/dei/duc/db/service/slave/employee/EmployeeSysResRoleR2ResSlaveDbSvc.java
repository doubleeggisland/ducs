package com.ioiox.dei.duc.db.service.slave.employee;

import com.ioiox.dei.core.orm.mybatis.service.IBaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.SysResRoleR2Res;

import java.util.List;
import java.util.Map;

public interface EmployeeSysResRoleR2ResSlaveDbSvc
        extends IBaseDeiSlaveDbService<SysResRoleR2Res> {

    Map<Long, List<Long>> getGroupedSysResIds(final List<Long> sysResRoleIds);
    Map<Long, List<Long>> getGroupedSysResRoleIds(final List<Long> sysResIds);
}
