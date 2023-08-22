package com.ioiox.dei.duc.db.service.slave.tenant;

import com.ioiox.dei.core.orm.mybatis.service.db.IBaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.RoleR2MenuSysApi;

import java.util.List;
import java.util.Map;

public interface TenantUserRoleR2MenuSysApiSlaveDbSvc
        extends IBaseDeiSlaveDbService<RoleR2MenuSysApi> {

    Map<Long, List<Long>> getGroupedMappingSids(final List<Long> roleIds);
    Map<Long, List<Long>> getGroupedRoleSids(final List<Long> sysApiIds);
}
