package com.ioiox.dei.duc.db.service.slave.employee;

import com.ioiox.dei.core.orm.mybatis.service.IBaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.UserGrpR2Role;

import java.util.List;
import java.util.Map;

public interface EmployeeUserGrpR2RoleSlaveDbSvc
        extends IBaseDeiSlaveDbService<UserGrpR2Role> {

    Map<Long, List<Long>> getGroupedRoleIds(final List<Long> employeeUserGrpIds);
    Map<Long, List<Long>> getGroupedEmployeeUserGrpIds(final List<Long> roleIds);
}
