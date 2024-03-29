package com.ioiox.dei.duc.db.service.slave.employee;

import com.ioiox.dei.core.orm.mybatis.service.db.IBaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.EmployeeR2Role;

import java.util.List;
import java.util.Map;

public interface EmployeeR2RoleSlaveDbSvc
        extends IBaseDeiSlaveDbService<EmployeeR2Role> {

    Map<Long, List<Long>> getGroupedRoleIds(final List<Long> employeeIds);
    Map<Long, List<Long>> getGroupedEmployeeIds(final List<Long> roleIds);
}
