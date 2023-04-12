package com.ioiox.dei.duc.db.service.slave.tenant;

import com.ioiox.dei.core.orm.mybatis.service.IBaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.RoleR2SysApi;

import java.util.List;
import java.util.Map;

public interface TenantUserRoleR2SysApiSlaveDbSvc
        extends IBaseDeiSlaveDbService<RoleR2SysApi> {

    Map<Long, List<Long>> getGroupedMappingSids(final List<Long> roleIds);
    Map<Long, List<Long>> getGroupedRoleSids(final List<Long> sysApiIds);
}
