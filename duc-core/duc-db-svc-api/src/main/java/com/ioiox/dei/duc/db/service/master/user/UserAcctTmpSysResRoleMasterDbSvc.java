package com.ioiox.dei.duc.db.service.master.user;

import com.ioiox.dei.core.orm.mybatis.service.db.IBaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.UserAcctTmpSysResRole;

import java.util.Date;
import java.util.List;

public interface UserAcctTmpSysResRoleMasterDbSvc
        extends IBaseDeiMasterDbService<UserAcctTmpSysResRole> {
    int assignSysResourcesToTmpSysResRole(final List<Long> sysResSids, final Long tmpSysResRoleSid, final String operator, final Date operateTime);
    int removeSysResourcesFromTmpSysResRoles(final List<Long> sysResSids, final List<Long> tmpSysResRoleSids);
}
