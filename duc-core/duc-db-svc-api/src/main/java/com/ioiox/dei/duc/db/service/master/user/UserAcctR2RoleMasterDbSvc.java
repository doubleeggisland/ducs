package com.ioiox.dei.duc.db.service.master.user;

import com.ioiox.dei.core.orm.mybatis.service.db.IBaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.UserAcctR2Role;

import java.util.Date;
import java.util.List;

public interface UserAcctR2RoleMasterDbSvc
        extends IBaseDeiMasterDbService<UserAcctR2Role> {
    int save(final List<Long> roleSids, final Long userAcctSid, final String operator, final Date operateTime);
}
