package com.ioiox.dei.duc.db.service.slave.user;

import com.ioiox.dei.core.orm.mybatis.service.db.IBaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.RoleR2MenuSysApi;

import java.util.List;
import java.util.Map;

public interface UserAcctTmpRoleR2MenuSysApiSlaveDbSvc
        extends IBaseDeiSlaveDbService<RoleR2MenuSysApi> {

    Map<Long, List<Long>> getGroupedMappingSids(final List<Long> tmpRoleIds);
    Map<Long, List<Long>> getGroupedTmpRoleSids(final List<Long> sysApiIds);
}
