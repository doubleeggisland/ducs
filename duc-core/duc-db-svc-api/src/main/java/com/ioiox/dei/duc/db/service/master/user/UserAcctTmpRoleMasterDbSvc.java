package com.ioiox.dei.duc.db.service.master.user;

import com.ioiox.dei.core.orm.mybatis.service.db.IBaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.UserAcctTmpRole;

import java.util.Date;
import java.util.List;

public interface UserAcctTmpRoleMasterDbSvc
        extends IBaseDeiMasterDbService<UserAcctTmpRole> {
    int assignMenusToTmpRole(final List<Long> menuSids, final Long tmpRoleSid, final String operator, final Date operateTime);
    int removeMenusFromTmpRoles(final List<Long> menuSids, final List<Long> tmpRoleSids);

    int assignMenuSysApisToTmpRole(final List<Long> sysApiMappingSids, final Long tmpRoleSid, final String operator, final Date operateTime);
    int removeMenuSysApisFromTmpRoles(final List<Long> sysApiMappingSids, final List<Long> tmpRoleSids);

    int assignSysApisToTmpRole(final List<Long> sysApiSids, final Long tmpRoleSid, final String operator, final Date operateTime);
    int removeSysApisFromTmpRoles(final List<Long> sysApiSids, final List<Long> tmpRoleSids);
}
