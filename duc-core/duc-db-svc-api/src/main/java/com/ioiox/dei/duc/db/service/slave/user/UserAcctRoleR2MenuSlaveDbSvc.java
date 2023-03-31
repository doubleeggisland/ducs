package com.ioiox.dei.duc.db.service.slave.user;

import com.ioiox.dei.core.orm.mybatis.service.IBaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.RoleR2Menu;

import java.util.List;
import java.util.Map;

public interface UserAcctRoleR2MenuSlaveDbSvc
        extends IBaseDeiSlaveDbService<RoleR2Menu> {
    Map<Long, List<Long>> getGroupedMenuIds(final List<Long> roleIds);
    Map<Long, List<Long>> getGroupedRoleIds(final List<Long> menuIds);
}
