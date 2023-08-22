package com.ioiox.dei.duc.db.service.slave.tenant;

import com.ioiox.dei.core.orm.mybatis.service.db.IBaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.UserGrpR2Role;

import java.util.List;
import java.util.Map;

public interface TenantUserGrpR2RoleSlaveDbSvc
        extends IBaseDeiSlaveDbService<UserGrpR2Role> {

    Map<Long, List<Long>> getGroupedRoleIds(final List<Long> tenantUserGrpIds);
    Map<Long, List<Long>> getGroupedTenantUserGrpIds(final List<Long> roleIds);
}
