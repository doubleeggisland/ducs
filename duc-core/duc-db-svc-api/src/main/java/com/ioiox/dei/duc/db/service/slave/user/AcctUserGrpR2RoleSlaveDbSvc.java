package com.ioiox.dei.duc.db.service.slave.user;

import com.ioiox.dei.core.orm.mybatis.service.IBaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.UserGrpR2Role;

import java.util.List;
import java.util.Map;

public interface AcctUserGrpR2RoleSlaveDbSvc
        extends IBaseDeiSlaveDbService<UserGrpR2Role> {

    Map<Long, List<Long>> getGroupedRoleIds(final List<Long> acctUserGrpIds);
    Map<Long, List<Long>> getGroupedAcctUserGrpIds(final List<Long> roleIds);
}
