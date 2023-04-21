package com.ioiox.dei.duc.db.service.master.user;

import com.ioiox.dei.core.orm.mybatis.service.IBaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.UserGrpR2Role;

import java.util.Date;
import java.util.List;

public interface AcctUserGrpR2RoleMasterDbSvc extends IBaseDeiMasterDbService<UserGrpR2Role> {
    int save(final List<Long> roleSids, final Long userGrpSid, final String operator, final Date operateTime);
}
