package com.ioiox.dei.duc.db.service.slave.user;

import com.ioiox.dei.core.orm.mybatis.service.IBaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.UserGrpR2SysResRole;

import java.util.List;
import java.util.Map;

public interface AcctUserGrpR2SysResRoleSlaveDbSvc
        extends IBaseDeiSlaveDbService<UserGrpR2SysResRole> {

    Map<Long, List<Long>> getGroupedSysResRoleIds(final List<Long> acctUserGrpIds);
    Map<Long, List<Long>> getGroupedAcctUserGrpIds(final List<Long> sysResRoleIds);
}
