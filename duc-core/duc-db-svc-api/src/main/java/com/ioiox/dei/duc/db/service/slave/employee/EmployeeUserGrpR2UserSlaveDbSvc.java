package com.ioiox.dei.duc.db.service.slave.employee;

import com.ioiox.dei.core.orm.mybatis.service.IBaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.UserGrpR2User;

import java.util.List;
import java.util.Map;

public interface EmployeeUserGrpR2UserSlaveDbSvc
        extends IBaseDeiSlaveDbService<UserGrpR2User> {

    Map<Long, List<Long>> getGroupedUserGrpIds(final List<Long> employeeIds);
    Map<Long, List<Long>> getGroupedEmployeeIds(final List<Long> employeeUserGrpIds);
}
