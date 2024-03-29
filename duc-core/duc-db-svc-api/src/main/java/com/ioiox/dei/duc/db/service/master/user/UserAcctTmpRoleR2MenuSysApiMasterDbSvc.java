package com.ioiox.dei.duc.db.service.master.user;

import com.ioiox.dei.core.orm.mybatis.service.db.IBaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.RoleR2MenuSysApi;

import java.util.Date;
import java.util.List;

public interface UserAcctTmpRoleR2MenuSysApiMasterDbSvc extends IBaseDeiMasterDbService<RoleR2MenuSysApi> {
    int save(final List<Long> sysApiMappingSids, final Long tmpRoleSid, final String operator, final Date operateTime);
}
