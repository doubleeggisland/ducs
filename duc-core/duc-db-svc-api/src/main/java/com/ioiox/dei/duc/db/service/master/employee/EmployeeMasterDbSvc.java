package com.ioiox.dei.duc.db.service.master.employee;

import com.ioiox.dei.core.orm.mybatis.service.db.IBaseDeiMasterDbService;
import com.ioiox.dei.duc.beans.entity.Employee;

import java.util.Date;
import java.util.List;

public interface EmployeeMasterDbSvc
        extends IBaseDeiMasterDbService<Employee> {

    int assignUserGrpsToUser(final List<Long> userGrpSids, final Long employeeSid, final String operator, final Date operateTime);
    int removeUserGrpsFromUsers(final List<Long> userGrpSids, final List<Long> employeeSids);

    int assignRolesToUser(final List<Long> roleSids, final Long employeeSid, final String operator, final Date operateTime);
    int removeRolesFromUsers(final List<Long> roleSids, final List<Long> employeeSids);

    int assignSysResRolesToUser(final List<Long> sysResRoleSids, final Long employeeSid, final String operator, final Date operateTime);
    int removeSysResRolesFromUsers(final List<Long> sysResRoleSids, final List<Long> employeeSids);

    int assignTmpRolesToUser(final List<Long> tmpRoleSids, final Long employeeSid, final String operator, final Date operateTime);
    int removeTmpRolesFromUsers(final List<Long> tmpRoleSids, final List<Long> employeeSids);

    int assignTmpSysResRolesToUser(final List<Long> tmpSysResRoleSids, final Long employeeSid, final String operator, final Date operateTime);
    int removeTmpSysResRolesFromUsers(final List<Long> tmpSysResRoleSids, final List<Long> employeeSids);
}
