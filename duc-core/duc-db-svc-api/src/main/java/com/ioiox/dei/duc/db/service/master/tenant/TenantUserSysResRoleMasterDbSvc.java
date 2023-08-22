package com.ioiox.dei.duc.db.service.master.tenant;

import com.ioiox.dei.core.orm.mybatis.service.db.IBaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.TenantUserSysResRole;

import java.util.Date;
import java.util.List;

public interface TenantUserSysResRoleMasterDbSvc
        extends IBaseDeiMasterDbService<TenantUserSysResRole> {
    int assignSysResourcesToSysResRole(final List<Long> sysResSids, final Long sysResRoleSid, final String operator, final Date operateTime);
    int removeSysResourcesFromSysResRoles(final List<Long> sysResSids, final List<Long> sysResRoleSids);
}
