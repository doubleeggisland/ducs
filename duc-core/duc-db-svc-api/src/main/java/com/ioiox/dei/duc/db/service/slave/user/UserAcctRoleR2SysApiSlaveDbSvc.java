package com.ioiox.dei.duc.db.service.slave.user;

import com.ioiox.dei.core.orm.mybatis.service.IBaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.RoleR2SysApi;

import java.util.List;
import java.util.Map;

public interface UserAcctRoleR2SysApiSlaveDbSvc
        extends IBaseDeiSlaveDbService<RoleR2SysApi> {
    Map<Long, List<Long>> getGroupedSysApiSids(final List<Long> roleSids);
    Map<Long, List<Long>> getGroupedRoleSids(final List<Long> sysApiSids);
}
