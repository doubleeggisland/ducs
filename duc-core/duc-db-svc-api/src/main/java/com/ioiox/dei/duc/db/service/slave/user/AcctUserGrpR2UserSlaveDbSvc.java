package com.ioiox.dei.duc.db.service.slave.user;

import com.ioiox.dei.core.orm.mybatis.service.IBaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.UserGrpR2User;

import java.util.List;
import java.util.Map;

public interface AcctUserGrpR2UserSlaveDbSvc
        extends IBaseDeiSlaveDbService<UserGrpR2User> {

    Map<Long, List<Long>> getGroupedUserGrpIds(final List<Long> acctUserIds);
    Map<Long, List<Long>> getGroupedAcctUserIds(final List<Long> acctUserGrpIds);
}
