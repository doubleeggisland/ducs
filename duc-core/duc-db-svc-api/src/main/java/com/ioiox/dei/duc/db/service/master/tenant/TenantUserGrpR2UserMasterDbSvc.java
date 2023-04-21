package com.ioiox.dei.duc.db.service.master.tenant;

import com.ioiox.dei.core.orm.mybatis.service.IBaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.UserGrpR2User;

import java.util.Date;
import java.util.List;

public interface TenantUserGrpR2UserMasterDbSvc
        extends IBaseDeiMasterDbService<UserGrpR2User> {
    int save(final List<Long> userGrpSids, final Long tenantUserSid, final String operator, final Date operateTime);
}
