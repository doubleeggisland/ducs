package com.ioiox.dei.duc.db.service.master.tenant;

import com.ioiox.dei.core.orm.mybatis.service.db.IBaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.TenantUserR2Role;

import java.util.Date;
import java.util.List;

public interface TenantUserR2TmpRoleMasterDbSvc
        extends IBaseDeiMasterDbService<TenantUserR2Role> {
    int save(final List<Long> tmpRoleSids, final Long tenantUserSid, final String operator, final Date operateTime);
}
