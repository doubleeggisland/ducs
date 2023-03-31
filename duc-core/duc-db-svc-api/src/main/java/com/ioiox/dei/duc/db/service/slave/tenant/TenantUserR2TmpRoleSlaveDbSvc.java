package com.ioiox.dei.duc.db.service.slave.tenant;

import com.ioiox.dei.core.orm.mybatis.service.IBaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.TenantUserR2Role;

import java.util.List;
import java.util.Map;

public interface TenantUserR2TmpRoleSlaveDbSvc
        extends IBaseDeiSlaveDbService<TenantUserR2Role> {

    Map<Long, List<Long>> getGroupedTmpRoleIds(final List<Long> tenantUserIds);
    Map<Long, List<Long>> getGroupedTenantUserIds(final List<Long> tmpRoleIds);
}
