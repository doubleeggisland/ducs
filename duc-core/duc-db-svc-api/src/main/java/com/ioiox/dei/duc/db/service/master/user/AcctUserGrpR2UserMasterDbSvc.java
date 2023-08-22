package com.ioiox.dei.duc.db.service.master.user;

import com.ioiox.dei.core.orm.mybatis.service.db.IBaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.UserGrpR2User;

import java.util.Date;
import java.util.List;

public interface AcctUserGrpR2UserMasterDbSvc
        extends IBaseDeiMasterDbService<UserGrpR2User> {
    int save(final List<Long> userGrpSids, final Long userAcctSid, final String operator, final Date operateTime);
}
