package com.ioiox.dei.duc.db.service.master.user;

import com.ioiox.dei.core.orm.mybatis.service.db.IBaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.UserAcctSysResRole;

import java.util.Date;
import java.util.List;

public interface UserAcctSysResRoleMasterDbSvc
        extends IBaseDeiMasterDbService<UserAcctSysResRole> {
    int assignSysResourcesToSysResRole(final List<Long> sysResSids, final Long sysResRoleSid, final String operator, final Date operateTime);
    int removeSysResourcesFromSysResRoles(final List<Long> sysResSids, final List<Long> sysResRoleSids);
}
