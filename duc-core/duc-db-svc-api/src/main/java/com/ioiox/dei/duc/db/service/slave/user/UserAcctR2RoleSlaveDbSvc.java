package com.ioiox.dei.duc.db.service.slave.user;

import com.ioiox.dei.core.orm.mybatis.service.IBaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.UserAcctR2Role;

import java.util.List;
import java.util.Map;

public interface UserAcctR2RoleSlaveDbSvc
        extends IBaseDeiSlaveDbService<UserAcctR2Role> {

    Map<Long, List<Long>> getGroupedRoleIds(final List<Long> userAcctIds);
    Map<Long, List<Long>> getGroupedUserAcctIds(final List<Long> roleIds);
}
