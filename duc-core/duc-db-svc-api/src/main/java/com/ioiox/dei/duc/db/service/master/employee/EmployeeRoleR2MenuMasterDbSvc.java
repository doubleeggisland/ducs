package com.ioiox.dei.duc.db.service.master.employee;

import com.ioiox.dei.core.orm.mybatis.service.IBaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.RoleR2Menu;

import java.util.Date;
import java.util.List;

public interface EmployeeRoleR2MenuMasterDbSvc
        extends IBaseDeiMasterDbService<RoleR2Menu> {
    int save(final List<Long> menuSids, final Long roleSid, final String operator, final Date operateTime);
}
