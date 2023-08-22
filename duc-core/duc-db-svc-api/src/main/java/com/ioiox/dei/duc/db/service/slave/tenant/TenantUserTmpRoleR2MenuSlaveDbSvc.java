package com.ioiox.dei.duc.db.service.slave.tenant;

import com.ioiox.dei.core.orm.mybatis.service.db.IBaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.RoleR2Menu;

import java.util.List;
import java.util.Map;

public interface TenantUserTmpRoleR2MenuSlaveDbSvc
        extends IBaseDeiSlaveDbService<RoleR2Menu> {

    Map<Long, List<Long>> getGroupedMenuIds(final List<Long> tmpRoleIds);
    Map<Long, List<Long>> getGroupedTmpRoleIds(final List<Long> menuIds);
}
