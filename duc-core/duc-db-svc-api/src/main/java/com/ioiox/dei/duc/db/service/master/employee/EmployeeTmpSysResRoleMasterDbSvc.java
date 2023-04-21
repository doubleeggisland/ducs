package com.ioiox.dei.duc.db.service.master.employee;

import com.ioiox.dei.core.orm.mybatis.service.IBaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.EmployeeTmpSysResRole;

import java.util.Date;
import java.util.List;

public interface EmployeeTmpSysResRoleMasterDbSvc
        extends IBaseDeiMasterDbService<EmployeeTmpSysResRole> {
    int assignSysResourcesToTmpSysResRole(final List<Long> sysResSids, final Long tmpSysResRoleSid, final String operator, final Date operateTime);
    int removeSysResourcesFromTmpSysResRoles(final List<Long> sysResSids, final List<Long> tmpSysResRoleSids);
}
