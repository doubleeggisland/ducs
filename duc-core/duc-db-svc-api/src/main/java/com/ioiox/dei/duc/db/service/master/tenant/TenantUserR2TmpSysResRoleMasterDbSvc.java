package com.ioiox.dei.duc.db.service.master.tenant;

import com.ioiox.dei.core.orm.mybatis.service.IBaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.TenantUserR2SysResRole;

import java.util.Date;
import java.util.List;

public interface TenantUserR2TmpSysResRoleMasterDbSvc
        extends IBaseDeiMasterDbService<TenantUserR2SysResRole> {
    int save(final List<Long> tmpSysResRoleSids, final Long tenantUserSid, final String operator, final Date operateTime);
}
