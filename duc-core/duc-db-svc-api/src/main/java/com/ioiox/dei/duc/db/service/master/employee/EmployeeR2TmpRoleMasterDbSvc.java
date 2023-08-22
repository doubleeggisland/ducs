package com.ioiox.dei.duc.db.service.master.employee;

import com.ioiox.dei.core.orm.mybatis.service.db.IBaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.EmployeeR2Role;

import java.util.Date;
import java.util.List;

public interface EmployeeR2TmpRoleMasterDbSvc
        extends IBaseDeiMasterDbService<EmployeeR2Role> {
    int save(final List<Long> tmpRoleSids, final Long employeeSid, final String operator, final Date operateTime);
}
