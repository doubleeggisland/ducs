package com.ioiox.dei.duc.db.service.master.employee;

import com.ioiox.dei.core.orm.mybatis.service.IBaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.RoleR2SysApi;

import java.util.Date;
import java.util.List;

public interface EmployeeRoleR2SysApiMasterDbSvc
        extends IBaseDeiMasterDbService<RoleR2SysApi> {
    int save(final List<Long> sysApiSids, final Long roleSid, final String operator, final Date operateTime);
}
