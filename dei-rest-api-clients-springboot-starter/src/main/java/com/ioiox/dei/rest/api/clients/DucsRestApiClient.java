package com.ioiox.dei.rest.api.clients;

import com.ioiox.dei.duc.beans.model.master.MenuDelParam;
import com.ioiox.dei.duc.beans.model.master.SysApiDelParam;
import com.ioiox.dei.duc.beans.model.master.SysPrjDelParam;
import com.ioiox.dei.duc.beans.model.master.SysResDelParam;
import com.ioiox.dei.duc.beans.model.master.employee.*;
import com.ioiox.dei.duc.beans.model.master.tenant.*;
import com.ioiox.dei.duc.beans.model.master.user.AcctUserGrpDelParam;
import com.ioiox.dei.duc.beans.model.master.user.UserAcctDelParam;
import com.ioiox.dei.duc.beans.model.master.user.UserAcctRoleDelParam;
import com.ioiox.dei.duc.beans.model.master.user.UserAcctSysResRoleDelParam;
import com.ioiox.dei.duc.beans.vo.std.master.MenuMasterVO;
import com.ioiox.dei.duc.beans.vo.std.master.SysApiMasterVO;
import com.ioiox.dei.duc.beans.vo.std.master.SysPrjMasterVO;
import com.ioiox.dei.duc.beans.vo.std.master.SysResMasterVO;
import com.ioiox.dei.duc.beans.vo.std.master.employee.*;
import com.ioiox.dei.duc.beans.vo.std.master.tenant.*;
import com.ioiox.dei.duc.beans.vo.std.master.user.*;

public interface DucsRestApiClient {

    Long saveMenu(final MenuMasterVO menu);

    void updateMenu(final Long id, final MenuMasterVO menu);

    Integer removeMenus(final MenuDelParam delParam);

    Long saveSysApi(final SysApiMasterVO sysApi);

    void updateSysApi(final Long id, final SysApiMasterVO sysApi);

    Integer removeSysApis(final SysApiDelParam delParam);

    Long saveSysResource(final SysResMasterVO sysRes);

    void updateSysResource(final Long id, final SysResMasterVO sysRes);

    Integer removeSysResources(final SysResDelParam delParam);

    Long saveSysPrj(final SysPrjMasterVO sysPrj);

    void updateSysPrj(final Long id, final SysPrjMasterVO sysPrj);

    Integer removeSysPrjs(final SysPrjDelParam delParam);

    Long saveAcctUserGrp(final AcctUserGrpMasterVO userGrp);

    void updateAcctUserGrp(final Long id, final AcctUserGrpMasterVO userGrp);

    Integer removeAcctUserGrps(final AcctUserGrpDelParam delParam);

    Long saveUserAcct(final UserAcctMasterVO userAcct);

    void updateUserAcct(final Long id, final UserAcctMasterVO userAcct);

    Integer removeUserAccts(final UserAcctDelParam delParam);

    Long saveUserAcctRole(final UserAcctRoleMasterVO role);

    void updateUserAcctRole(final Long id, final UserAcctRoleMasterVO role);

    Integer removeUserAcctRoles(final UserAcctRoleDelParam delParam);

    Long saveUserAcctTmpRole(final UserAcctTmpRoleMasterVO tmpRole);

    void updateUserAcctTmpRole(final Long id, final UserAcctTmpRoleMasterVO tmpRole);

    Integer removeUserAcctTmpRoles(final UserAcctRoleDelParam delParam);

    Long saveUserAcctSysResRole(final UserAcctSysResRoleMasterVO sysResRole);

    void updateUserAcctSysResRole(final Long id, final UserAcctSysResRoleMasterVO sysResRole);

    Integer removeUserAcctSysResRoles(final UserAcctSysResRoleDelParam delParam);

    Long saveUserAcctTmpSysResRole(final UserAcctTmpSysResRoleMasterVO tmpSysResRole);

    void updateUserAcctTmpSysResRole(final Long id, final UserAcctTmpSysResRoleMasterVO tmpSysResRole);

    Integer removeUserAcctTmpSysResRoles(final UserAcctSysResRoleDelParam delParam);

    Long saveTenant(final TenantMasterVO tenant);

    void updateTenant(final Long id, final TenantMasterVO tenant);

    Integer removeTenants(final TenantDelParam delParam);

    Long saveTenantUserGrp(final TenantUserGrpMasterVO userGrp);

    void updateTenantUserGrp(final Long id, final TenantUserGrpMasterVO userGrp);

    Integer removeTenantUserGrps(final TenantUserGrpDelParam delParam);

    Long saveTenantUser(final TenantUserMasterVO tenantUser);

    void updateTenantUser(final Long id, final TenantUserMasterVO tenantUser);

    Integer removeTenantUsers(final TenantUserDelParam delParam);

    Long saveTenantUserRole(final TenantUserRoleMasterVO role);

    void updateTenantUserRole(final Long id, final TenantUserRoleMasterVO role);

    Integer removeTenantUserRoles(final TenantUserRoleDelParam delParam);

    Long saveTenantUserTmpRole(final TenantUserTmpRoleMasterVO tmpRole);

    void updateTenantUserTmpRole(final Long id, final TenantUserTmpRoleMasterVO tmpRole);

    Integer removeTenantUserTmpRoles(final TenantUserTmpRoleDelParam delParam);

    Long saveTenantUserSysResRole(final TenantUserSysResRoleMasterVO sysResRole);

    void updateTenantUserSysResRole(final Long id, final TenantUserSysResRoleMasterVO sysResRole);

    Integer removeTenantUserSysResRoles(final TenantUserSysResRoleDelParam delParam);

    Long saveTenantUserTmpSysResRole(final TenantUserTmpSysResRoleMasterVO tmpSysResRole);

    void updateTenantUserTmpSysResRole(final Long id, final TenantUserTmpSysResRoleMasterVO tmpSysResRole);

    Integer removeTenantUserTmpSysResRoles(final TenantUserTmpSysResRoleDelParam delParam);

    Long saveEmployeeUserGrp(final EmployeeUserGrpMasterVO userGrp);

    void updateEmployeeUserGrp(final Long id, final EmployeeUserGrpMasterVO userGrp);

    Integer removeEmployeeUserGrps(final EmployeeUserGrpDelParam delParam);

    Long saveEmployee(final EmployeeMasterVO employee);

    void updateEmployee(final Long id, final EmployeeMasterVO employee);

    Integer removeEmployees(final EmployeeDelParam delParam);

    Long saveEmployeeRole(final EmployeeRoleMasterVO role);

    void updateEmployeeRole(final Long id, final EmployeeRoleMasterVO role);

    Integer removeEmployeeRoles(final EmployeeRoleDelParam delParam);

    Long saveEmployeeTmpRole(final EmployeeTmpRoleMasterVO tmpRole);

    void updateEmployeeTmpRole(final Long id, final EmployeeTmpRoleMasterVO tmpRole);

    Integer removeEmployeeTmpRoles(final EmployeeTmpRoleDelParam delParam);

    Long saveEmployeeSysResRole(final EmployeeSysResRoleMasterVO sysResRole);

    void updateEmployeeSysResRole(final Long id, final EmployeeSysResRoleMasterVO sysResRole);

    Integer removeEmployeeSysResRoles(final EmployeeSysResRoleDelParam delParam);

    Long saveEmployeeTmpSysResRole(final EmployeeTmpSysResRoleMasterVO tmpSysResRole);

    void updateEmployeeTmpSysResRole(final Long id, final EmployeeTmpSysResRoleMasterVO tmpSysResRole);

    Integer removeEmployeeTmpSysResRoles(final EmployeeTmpSysResRoleDelParam delParam);
}
