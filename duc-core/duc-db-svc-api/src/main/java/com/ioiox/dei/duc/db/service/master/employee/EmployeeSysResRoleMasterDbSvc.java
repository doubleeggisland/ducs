package com.ioiox.dei.duc.db.service.master.employee;

import com.ioiox.dei.core.orm.mybatis.service.IBaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.EmployeeSysResRole;

import java.util.Date;
import java.util.List;

public interface EmployeeSysResRoleMasterDbSvc
        extends IBaseDeiMasterDbService<EmployeeSysResRole> {
    int assignSysResourcesToSysResRole(final List<Long> sysResSids, final Long sysResRoleSid, final String operator, final Date operateTime);
    int removeSysResourcesFromSysResRoles(final List<Long> sysResSids, final List<Long> sysResRoleSids);
}
