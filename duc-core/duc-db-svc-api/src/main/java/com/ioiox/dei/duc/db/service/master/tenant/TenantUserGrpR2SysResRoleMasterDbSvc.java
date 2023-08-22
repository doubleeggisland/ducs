package com.ioiox.dei.duc.db.service.master.tenant;

import com.ioiox.dei.core.orm.mybatis.service.db.IBaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.UserGrpR2SysResRole;

import java.util.Date;
import java.util.List;

public interface TenantUserGrpR2SysResRoleMasterDbSvc
        extends IBaseDeiMasterDbService<UserGrpR2SysResRole> {
    int save(final List<Long> sysResRoleSids, final Long userGrpSid, final String operator, final Date operateTime);
}
