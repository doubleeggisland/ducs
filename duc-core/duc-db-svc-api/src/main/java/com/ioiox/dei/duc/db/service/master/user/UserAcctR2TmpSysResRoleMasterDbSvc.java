package com.ioiox.dei.duc.db.service.master.user;

import com.ioiox.dei.core.orm.mybatis.service.IBaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.UserAcctR2SysResRole;

import java.util.Date;
import java.util.List;

public interface UserAcctR2TmpSysResRoleMasterDbSvc
        extends IBaseDeiMasterDbService<UserAcctR2SysResRole> {
    int save(final List<Long> tmpSysResRoleSids, final Long userAcctSid, final String operator, final Date operateTime);
}
