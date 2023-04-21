package com.ioiox.dei.duc.db.service.master.tenant;

import com.ioiox.dei.core.orm.mybatis.service.IBaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.TenantUserTmpSysResRole;

import java.util.Date;
import java.util.List;

public interface TenantUserTmpSysResRoleMasterDbSvc
        extends IBaseDeiMasterDbService<TenantUserTmpSysResRole> {
    int assignSysResourcesToTmpSysResRole(final List<Long> sysResSids, final Long tmpSysResRoleSid, final String operator, final Date operateTime);
    int removeSysResourcesFromTmpSysResRoles(final List<Long> sysResSids, final List<Long> tmpSysResRoleSids);
}
