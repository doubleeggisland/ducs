package com.ioiox.dei.duc.db.service.master.user;

import com.ioiox.dei.core.orm.mybatis.service.IBaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.RoleR2Menu;

import java.util.Date;
import java.util.List;

public interface UserAcctTmpRoleR2MenuMasterDbSvc extends IBaseDeiMasterDbService<RoleR2Menu> {
    int save(final List<Long> menuSids, final Long tmpRoleSid, final String operator, final Date operateTime);
}
