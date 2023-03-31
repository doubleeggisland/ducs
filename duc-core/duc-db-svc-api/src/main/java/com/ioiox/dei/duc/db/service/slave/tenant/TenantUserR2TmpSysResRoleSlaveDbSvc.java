package com.ioiox.dei.duc.db.service.slave.tenant;

import com.ioiox.dei.core.orm.mybatis.service.IBaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.TenantUserR2SysResRole;

import java.util.List;
import java.util.Map;

public interface TenantUserR2TmpSysResRoleSlaveDbSvc
        extends IBaseDeiSlaveDbService<TenantUserR2SysResRole> {

    Map<Long, List<Long>> getGroupedTmpSysResRoleIds(final List<Long> tenantUserIds);
    Map<Long, List<Long>> getGroupedTenantUserIds(final List<Long> tmpSysResRoleIds);
}
