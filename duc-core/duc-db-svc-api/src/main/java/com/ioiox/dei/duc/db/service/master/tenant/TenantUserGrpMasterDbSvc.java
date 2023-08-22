package com.ioiox.dei.duc.db.service.master.tenant;

import com.ioiox.dei.core.orm.mybatis.service.db.IBaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.TenantUserGrp;

import java.util.Date;
import java.util.List;

public interface TenantUserGrpMasterDbSvc
        extends IBaseDeiMasterDbService<TenantUserGrp> {

    int assignRolesToUserGrp(final List<Long> roleSids, final Long userGrpSid, final String operator, final Date operateTime);
    int removeRolesFromUserGrps(final List<Long> roleSids, final List<Long> userGrpSids);

    int assignSysResRolesToUserGrp(final List<Long> sysResRoleSids, final Long userGrpSid, final String operator, final Date operateTime);
    int removeSysResRolesFromUserGrps(final List<Long> sysResRoleSids, final List<Long> userGrpSids);
}
