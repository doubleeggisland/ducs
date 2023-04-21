package com.ioiox.dei.duc.db.service.master.user;

import com.ioiox.dei.core.orm.mybatis.service.IBaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.AcctUserGrp;

import java.util.Date;
import java.util.List;

public interface AcctUserGrpMasterDbSvc extends IBaseDeiMasterDbService<AcctUserGrp> {
    int assignRolesToUserGrp(final List<Long> roleSids, final Long userGrpSid, final String operator, final Date operateTime);
    int removeRolesFromUserGrps(final List<Long> roleSids, final List<Long> userGrpSids);
    int assignSysResRolesToUserGrp(final List<Long> sysResRoleSids, final Long userGrpSid, final String operator, final Date operateTime);
    int removeSysResRolesFromUserGrps(final List<Long> sysResRoleSids, final List<Long> userGrpSids);
}
