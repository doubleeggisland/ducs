package com.ioiox.dei.duc.db.service.master.employee;

import com.ioiox.dei.core.orm.mybatis.service.db.IBaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.EmployeeUserGrp;

import java.util.Date;
import java.util.List;

public interface EmployeeUserGrpMasterDbSvc
        extends IBaseDeiMasterDbService<EmployeeUserGrp> {

    int assignRolesToUserGrp(final List<Long> roleSids, final Long userGrpSid, final String operator, final Date operateTime);
    int removeRolesFromUserGrps(final List<Long> roleSids, final List<Long> userGrpSids);

    int assignSysResRolesToUserGrp(final List<Long> sysResRoleSids, final Long userGrpSid, final String operator, final Date operateTime);
    int removeSysResRolesFromUserGrps(final List<Long> sysResRoleSids, final List<Long> userGrpSids);
}
