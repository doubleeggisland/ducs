package com.ioiox.dei.duc.db.service.master.tenant;

import com.ioiox.dei.core.orm.mybatis.service.IBaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.RoleR2MenuSysApi;

import java.util.Date;
import java.util.List;

public interface TenantUserRoleR2MenuSysApiMasterDbSvc
        extends IBaseDeiMasterDbService<RoleR2MenuSysApi> {
    int save(final List<Long> sysApiMappingSids, final Long roleSid, final String operator, final Date operateTime);
}
