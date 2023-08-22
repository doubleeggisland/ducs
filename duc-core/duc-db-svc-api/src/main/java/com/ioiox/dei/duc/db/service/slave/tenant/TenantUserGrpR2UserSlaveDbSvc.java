package com.ioiox.dei.duc.db.service.slave.tenant;

import com.ioiox.dei.core.orm.mybatis.service.db.IBaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.UserGrpR2User;

import java.util.List;
import java.util.Map;

public interface TenantUserGrpR2UserSlaveDbSvc
        extends IBaseDeiSlaveDbService<UserGrpR2User> {

    Map<Long, List<Long>> getGroupedUserGrpIds(final List<Long> tenantUserIds);
    Map<Long, List<Long>> getGroupedTenantUserIds(final List<Long> tenantUserGrpIds);
}
