package com.ioiox.dei.duc.db.service.master.tenant;

import com.ioiox.dei.core.orm.mybatis.service.db.IBaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.SysResRoleR2Res;

import java.util.Date;
import java.util.List;

public interface TenantUserTmpSysResRoleR2ResMasterDbSvc
        extends IBaseDeiMasterDbService<SysResRoleR2Res> {
    int save(final List<Long> sysResSids, final Long tmpSysResRoleSid, final String operator, final Date operateTime);
}
