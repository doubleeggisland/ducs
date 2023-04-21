package com.ioiox.dei.duc.db.service.master.tenant;

import com.ioiox.dei.core.orm.mybatis.service.IBaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.TenantUser;

import java.util.Date;
import java.util.List;

public interface TenantUserMasterDbSvc
        extends IBaseDeiMasterDbService<TenantUser> {

    int assignUserGrpsToUser(final List<Long> userGrpSids, final Long tenantUserSid, final String operator, final Date operateTime);
    int removeUserGrpsFromUsers(final List<Long> userGrpSids, final List<Long> tenantUserSids);

    int assignRolesToUser(final List<Long> roleSids, final Long tenantUserSid, final String operator, final Date operateTime);
    int removeRolesFromUsers(final List<Long> roleSids, final List<Long> tenantUserSids);

    int assignSysResRolesToUser(final List<Long> sysResRoleSids, final Long tenantUserSid, final String operator, final Date operateTime);
    int removeSysResRolesFromUsers(final List<Long> sysResRoleSids, final List<Long> tenantUserSids);

    int assignTmpRolesToUser(final List<Long> tmpRoleSids, final Long tenantUserSid, final String operator, final Date operateTime);
    int removeTmpRolesFromUsers(final List<Long> tmpRoleSids, final List<Long> tenantUserSids);

    int assignTmpSysResRolesToUser(final List<Long> tmpSysResRoleSids, final Long tenantUserSid, final String operator, final Date operateTime);
    int removeTmpSysResRolesFromUsers(final List<Long> tmpSysResRoleSids, final List<Long> tenantUserSids);
}
