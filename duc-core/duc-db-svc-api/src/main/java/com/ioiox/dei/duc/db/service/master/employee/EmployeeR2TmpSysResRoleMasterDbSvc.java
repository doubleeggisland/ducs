package com.ioiox.dei.duc.db.service.master.employee;

import com.ioiox.dei.core.orm.mybatis.service.db.IBaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.EmployeeR2SysResRole;

import java.util.Date;
import java.util.List;

public interface EmployeeR2TmpSysResRoleMasterDbSvc
        extends IBaseDeiMasterDbService<EmployeeR2SysResRole> {
    int save(final List<Long> tmpSysResRoleSids, final Long employeeSid, final String operator, final Date operateTime);
}
