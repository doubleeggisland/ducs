package com.ioiox.dei.duc.db.service.master.employee;

import com.ioiox.dei.core.orm.mybatis.service.db.IBaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.EmployeeRole;

import java.util.Date;
import java.util.List;

public interface EmployeeRoleMasterDbSvc
        extends IBaseDeiMasterDbService<EmployeeRole> {

    int assignMenusToRole(final List<Long> menuSids, final Long roleSid, final String operator, final Date operateTime);
    int removeMenusFromRoles(final List<Long> menuSids, final List<Long> roleSids);

    int assignMenuSysApisToRole(final List<Long> sysApiMappingSids, final Long roleSid, final String operator, final Date operateTime);
    int removeMenuSysApisFromRoles(final List<Long> sysApiMappingSids, final List<Long> roleSids);

    int assignSysApisToRole(final List<Long> sysApiSids, final Long roleSid, final String operator, final Date operateTime);
    int removeSysApisFromRoles(final List<Long> sysApiSids, final List<Long> roleSids);
}
