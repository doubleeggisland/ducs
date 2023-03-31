package com.ioiox.dei.duc.db.service.slave.user;

import com.ioiox.dei.core.orm.mybatis.service.IBaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.UserAcctR2SysResRole;

import java.util.List;
import java.util.Map;

public interface UserAcctR2SysResRoleSlaveDbSvc
        extends IBaseDeiSlaveDbService<UserAcctR2SysResRole> {

    Map<Long, List<Long>> getGroupedSysResRoleIds(final List<Long> userAcctIds);
    Map<Long, List<Long>> getGroupedUserAcctIds(final List<Long> sysResRoleIds);
}
