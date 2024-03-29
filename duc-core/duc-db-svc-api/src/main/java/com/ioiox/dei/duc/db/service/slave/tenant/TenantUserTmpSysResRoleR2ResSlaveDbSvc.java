package com.ioiox.dei.duc.db.service.slave.tenant;

import com.ioiox.dei.core.orm.mybatis.service.db.IBaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.SysResRoleR2Res;

import java.util.List;
import java.util.Map;

public interface TenantUserTmpSysResRoleR2ResSlaveDbSvc
        extends IBaseDeiSlaveDbService<SysResRoleR2Res> {

    Map<Long, List<Long>> getGroupedSysResIds(final List<Long> tmpSysResRoleIds);
    Map<Long, List<Long>> getGroupedTmpSysResRoleIds(final List<Long> sysResIds);
}
