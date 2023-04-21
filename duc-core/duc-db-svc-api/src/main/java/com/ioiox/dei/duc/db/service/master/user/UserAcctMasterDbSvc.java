package com.ioiox.dei.duc.db.service.master.user;

import com.ioiox.dei.core.orm.mybatis.service.IBaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.UserAcct;

import java.util.Date;
import java.util.List;

public interface UserAcctMasterDbSvc
        extends IBaseDeiMasterDbService<UserAcct> {

    int assignUserGrpsToUser(final List<Long> userGrpSids, final Long userAcctSid, final String operator, final Date operateTime);
    int removeUserGrpsFromUsers(final List<Long> userGrpSids, final List<Long> userAcctSids);

    int assignRolesToUser(final List<Long> roleSids, final Long userAcctSid, final String operator, final Date operateTime);
    int removeRolesFromUsers(final List<Long> roleSids, final List<Long> userAcctSids);

    int assignSysResRolesToUser(final List<Long> sysResRoleSids, final Long userAcctSid, final String operator, final Date operateTime);
    int removeSysResRolesFromUsers(final List<Long> sysResRoleSids, final List<Long> userAcctSids);

    int assignTmpRolesToUser(final List<Long> tmpRoleSids, final Long userAcctSid, final String operator, final Date operateTime);
    int removeTmpRolesFromUsers(final List<Long> tmpRoleSids, final List<Long> userAcctSids);

    int assignTmpSysResRolesToUser(final List<Long> tmpSysResRoleSids, final Long userAcctSid, final String operator, final Date operateTime);
    int removeTmpSysResRolesFromUsers(final List<Long> tmpSysResRoleSids, final List<Long> userAcctSids);
}
