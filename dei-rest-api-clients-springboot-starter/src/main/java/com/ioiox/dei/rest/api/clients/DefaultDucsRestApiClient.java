package com.ioiox.dei.rest.api.clients;


import com.ioiox.dei.core.vo.DeiResponseData;
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
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class DefaultDucsRestApiClient
        extends BaseDeiRestApiClient
        implements DucsRestApiClient {

    public DefaultDucsRestApiClient(final RestTemplate restTemplate, final String serverHost) {
        super(restTemplate, serverHost);
    }

    @Override
    public Long saveMenu(final MenuMasterVO menu) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<MenuMasterVO> httpEntity = new HttpEntity<>(menu, httpHeaders);
        final String url = String.format("%s/duc/common/api/menus/save", this.getServerHost());
        final ResponseEntity<DeiResponseData<Long>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Long>>() {}, true);
        final DeiResponseData<Long> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public void updateMenu(final Long id, final MenuMasterVO menu) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<MenuMasterVO> httpEntity = new HttpEntity<>(menu, httpHeaders);
        final String url = String.format("%s/duc/common/api/menus/%s/update", this.getServerHost(), id);
        final ResponseEntity<DeiResponseData<Long>> response = execute(url, HttpMethod.PUT, httpEntity, new ParameterizedTypeReference<DeiResponseData<Long>>() {}, true);
        checkResponse(response);
    }

    @Override
    public Integer removeMenus(final MenuDelParam delParam) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<MenuDelParam> httpEntity = new HttpEntity<>(delParam, httpHeaders);
        final String url = String.format("%s/duc/common/api/menus/remove", this.getServerHost());
        final ResponseEntity<DeiResponseData<Integer>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Integer>>() {}, true);
        final DeiResponseData<Integer> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public Long saveSysApi(final SysApiMasterVO sysApi) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<SysApiMasterVO> httpEntity = new HttpEntity<>(sysApi, httpHeaders);
        final String url = String.format("%s/duc/common/api/sys-apis/save", this.getServerHost());
        final ResponseEntity<DeiResponseData<Long>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Long>>() {}, true);
        final DeiResponseData<Long> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public void updateSysApi(final Long id, final SysApiMasterVO sysApi) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<SysApiMasterVO> httpEntity = new HttpEntity<>(sysApi, httpHeaders);
        final String url = String.format("%s/duc/common/api/sys-apis/%s/update", this.getServerHost(), id);
        final ResponseEntity<DeiResponseData<Long>> response = execute(url, HttpMethod.PUT, httpEntity, new ParameterizedTypeReference<DeiResponseData<Long>>() {}, true);
        checkResponse(response);
    }

    @Override
    public Integer removeSysApis(final SysApiDelParam delParam) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<SysApiDelParam> httpEntity = new HttpEntity<>(delParam, httpHeaders);
        final String url = String.format("%s/duc/common/api/sys-apis/remove", this.getServerHost());
        final ResponseEntity<DeiResponseData<Integer>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Integer>>() {}, true);
        final DeiResponseData<Integer> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public Long saveSysResource(final SysResMasterVO sysRes) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<SysResMasterVO> httpEntity = new HttpEntity<>(sysRes, httpHeaders);
        final String url = String.format("%s/duc/common/api/sys-resources/save", this.getServerHost());
        final ResponseEntity<DeiResponseData<Long>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Long>>() {}, true);
        final DeiResponseData<Long> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public void updateSysResource(final Long id, final SysResMasterVO sysRes) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<SysResMasterVO> httpEntity = new HttpEntity<>(sysRes, httpHeaders);
        final String url = String.format("%s/duc/common/api/sys-resources/%s/update", this.getServerHost(), id);
        final ResponseEntity<DeiResponseData<Long>> response = execute(url, HttpMethod.PUT, httpEntity, new ParameterizedTypeReference<DeiResponseData<Long>>() {}, true);
        checkResponse(response);
    }

    @Override
    public Integer removeSysResources(final SysResDelParam delParam) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<SysResDelParam> httpEntity = new HttpEntity<>(delParam, httpHeaders);
        final String url = String.format("%s/duc/common/api/sys-resources/remove", this.getServerHost());
        final ResponseEntity<DeiResponseData<Integer>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Integer>>() {}, true);
        final DeiResponseData<Integer> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public Long saveSysPrj(final SysPrjMasterVO sysPrj) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<SysPrjMasterVO> httpEntity = new HttpEntity<>(sysPrj, httpHeaders);
        final String url = String.format("%s/duc/common/api/sys-prjs/save", this.getServerHost());
        final ResponseEntity<DeiResponseData<Long>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Long>>() {}, true);
        final DeiResponseData<Long> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public void updateSysPrj(final Long id, final SysPrjMasterVO sysPrj) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<SysPrjMasterVO> httpEntity = new HttpEntity<>(sysPrj, httpHeaders);
        final String url = String.format("%s/duc/common/api/sys-prjs/%s/update", this.getServerHost(), id);
        final ResponseEntity<DeiResponseData<Long>> response = execute(url, HttpMethod.PUT, httpEntity, new ParameterizedTypeReference<DeiResponseData<Long>>() {}, true);
        checkResponse(response);
    }

    @Override
    public Integer removeSysPrjs(final SysPrjDelParam delParam) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<SysPrjDelParam> httpEntity = new HttpEntity<>(delParam, httpHeaders);
        final String url = String.format("%s/duc/common/api/sys-prjs/remove", this.getServerHost());
        final ResponseEntity<DeiResponseData<Integer>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Integer>>() {}, true);
        final DeiResponseData<Integer> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public Long saveAcctUserGrp(final AcctUserGrpMasterVO userGrp) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<AcctUserGrpMasterVO> httpEntity = new HttpEntity<>(userGrp, httpHeaders);
        final String url = String.format("%s/duc/common/api/user-acct/user-grps/save", this.getServerHost());
        final ResponseEntity<DeiResponseData<Long>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Long>>() {}, true);
        final DeiResponseData<Long> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public void updateAcctUserGrp(final Long id, final AcctUserGrpMasterVO userGrp) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<AcctUserGrpMasterVO> httpEntity = new HttpEntity<>(userGrp, httpHeaders);
        final String url = String.format("%s/duc/common/api/user-acct/user-grps/%s/update", this.getServerHost(), id);
        final ResponseEntity<DeiResponseData<Long>> response = execute(url, HttpMethod.PUT, httpEntity, new ParameterizedTypeReference<DeiResponseData<Long>>() {}, true);
        checkResponse(response);
    }

    @Override
    public Integer removeAcctUserGrps(final AcctUserGrpDelParam delParam) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<AcctUserGrpDelParam> httpEntity = new HttpEntity<>(delParam, httpHeaders);
        final String url = String.format("%s/duc/common/api/user-acct/user-grps/remove", this.getServerHost());
        final ResponseEntity<DeiResponseData<Integer>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Integer>>() {}, true);
        final DeiResponseData<Integer> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public Long saveUserAcct(final UserAcctMasterVO userAcct) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<UserAcctMasterVO> httpEntity = new HttpEntity<>(userAcct, httpHeaders);
        final String url = String.format("%s/duc/common/api/user-accts/save", this.getServerHost());
        final ResponseEntity<DeiResponseData<Long>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Long>>() {}, true);
        final DeiResponseData<Long> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public void updateUserAcct(final Long id, final UserAcctMasterVO userAcct) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<UserAcctMasterVO> httpEntity = new HttpEntity<>(userAcct, httpHeaders);
        final String url = String.format("%s/duc/common/api/user-accts/%s/update", this.getServerHost(), id);
        final ResponseEntity<DeiResponseData<Long>> response = execute(url, HttpMethod.PUT, httpEntity, new ParameterizedTypeReference<DeiResponseData<Long>>() {}, true);
        checkResponse(response);
    }

    @Override
    public Integer removeUserAccts(final UserAcctDelParam delParam) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<UserAcctDelParam> httpEntity = new HttpEntity<>(delParam, httpHeaders);
        final String url = String.format("%s/duc/common/api/user-accts/remove", this.getServerHost());
        final ResponseEntity<DeiResponseData<Integer>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Integer>>() {}, true);
        final DeiResponseData<Integer> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public Long saveUserAcctRole(final UserAcctRoleMasterVO role) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<UserAcctRoleMasterVO> httpEntity = new HttpEntity<>(role, httpHeaders);
        final String url = String.format("%s/duc/common/api/user-acct/roles/save", this.getServerHost());
        final ResponseEntity<DeiResponseData<Long>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Long>>() {}, true);
        final DeiResponseData<Long> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public void updateUserAcctRole(final Long id, final UserAcctRoleMasterVO role) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<UserAcctRoleMasterVO> httpEntity = new HttpEntity<>(role, httpHeaders);
        final String url = String.format("%s/duc/common/api/user-acct/roles/%s/update", this.getServerHost(), id);
        final ResponseEntity<DeiResponseData<Long>> response = execute(url, HttpMethod.PUT, httpEntity, new ParameterizedTypeReference<DeiResponseData<Long>>() {}, true);
        checkResponse(response);
    }

    @Override
    public Integer removeUserAcctRoles(final UserAcctRoleDelParam delParam) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<UserAcctRoleDelParam> httpEntity = new HttpEntity<>(delParam, httpHeaders);
        final String url = String.format("%s/duc/common/api/user-acct/roles/remove", this.getServerHost());
        final ResponseEntity<DeiResponseData<Integer>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Integer>>() {}, true);
        final DeiResponseData<Integer> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public Long saveUserAcctTmpRole(final UserAcctTmpRoleMasterVO tmpRole) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<UserAcctTmpRoleMasterVO> httpEntity = new HttpEntity<>(tmpRole, httpHeaders);
        final String url = String.format("%s/duc/common/api/user-acct/tmp-roles/save", this.getServerHost());
        final ResponseEntity<DeiResponseData<Long>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Long>>() {}, true);
        final DeiResponseData<Long> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public void updateUserAcctTmpRole(final Long id, final UserAcctTmpRoleMasterVO tmpRole) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<UserAcctTmpRoleMasterVO> httpEntity = new HttpEntity<>(tmpRole, httpHeaders);
        final String url = String.format("%s/duc/common/api/user-acct/tmp-roles/%s/update", this.getServerHost(), id);
        final ResponseEntity<DeiResponseData<Long>> response = execute(url, HttpMethod.PUT, httpEntity, new ParameterizedTypeReference<DeiResponseData<Long>>() {}, true);
        checkResponse(response);
    }

    @Override
    public Integer removeUserAcctTmpRoles(final UserAcctRoleDelParam delParam) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<UserAcctRoleDelParam> httpEntity = new HttpEntity<>(delParam, httpHeaders);
        final String url = String.format("%s/duc/common/api/user-acct/tmp-roles/remove", this.getServerHost());
        final ResponseEntity<DeiResponseData<Integer>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Integer>>() {}, true);
        final DeiResponseData<Integer> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public Long saveUserAcctSysResRole(final UserAcctSysResRoleMasterVO sysResRole) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<UserAcctSysResRoleMasterVO> httpEntity = new HttpEntity<>(sysResRole, httpHeaders);
        final String url = String.format("%s/duc/common/api/user-acct/sys-res-roles/save", this.getServerHost());
        final ResponseEntity<DeiResponseData<Long>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Long>>() {}, true);
        final DeiResponseData<Long> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public void updateUserAcctSysResRole(final Long id, final UserAcctSysResRoleMasterVO sysResRole) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<UserAcctSysResRoleMasterVO> httpEntity = new HttpEntity<>(sysResRole, httpHeaders);
        final String url = String.format("%s/duc/common/api/user-acct/sys-res-roles/%s/update", this.getServerHost(), id);
        final ResponseEntity<DeiResponseData<Long>> response = execute(url, HttpMethod.PUT, httpEntity, new ParameterizedTypeReference<DeiResponseData<Long>>() {}, true);
        checkResponse(response);
    }

    @Override
    public Integer removeUserAcctSysResRoles(final UserAcctSysResRoleDelParam delParam) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<UserAcctSysResRoleDelParam> httpEntity = new HttpEntity<>(delParam, httpHeaders);
        final String url = String.format("%s/duc/common/api/user-acct/sys-res-roles/remove", this.getServerHost());
        final ResponseEntity<DeiResponseData<Integer>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Integer>>() {}, true);
        final DeiResponseData<Integer> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public Long saveUserAcctTmpSysResRole(final UserAcctTmpSysResRoleMasterVO tmpSysResRole) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<UserAcctTmpSysResRoleMasterVO> httpEntity = new HttpEntity<>(tmpSysResRole, httpHeaders);
        final String url = String.format("%s/duc/common/api/user-acct/tmp-sys-res-roles/save", this.getServerHost());
        final ResponseEntity<DeiResponseData<Long>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Long>>() {}, true);
        final DeiResponseData<Long> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public void updateUserAcctTmpSysResRole(final Long id, final UserAcctTmpSysResRoleMasterVO tmpSysResRole) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<UserAcctTmpSysResRoleMasterVO> httpEntity = new HttpEntity<>(tmpSysResRole, httpHeaders);
        final String url = String.format("%s/duc/common/api/user-acct/tmp-sys-res-roles/%s/update", this.getServerHost(), id);
        final ResponseEntity<DeiResponseData<Long>> response = execute(url, HttpMethod.PUT, httpEntity, new ParameterizedTypeReference<DeiResponseData<Long>>() {}, true);
        checkResponse(response);
    }

    @Override
    public Integer removeUserAcctTmpSysResRoles(final UserAcctSysResRoleDelParam delParam) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<UserAcctSysResRoleDelParam> httpEntity = new HttpEntity<>(delParam, httpHeaders);
        final String url = String.format("%s/duc/common/api/user-acct/tmp-sys-res-roles/remove", this.getServerHost());
        final ResponseEntity<DeiResponseData<Integer>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Integer>>() {}, true);
        final DeiResponseData<Integer> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public Long saveTenant(final TenantMasterVO tenant) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<TenantMasterVO> httpEntity = new HttpEntity<>(tenant, httpHeaders);
        final String url = String.format("%s/duc/common/api/tenants/save", this.getServerHost());
        final ResponseEntity<DeiResponseData<Long>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Long>>() {}, true);
        final DeiResponseData<Long> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public void updateTenant(final Long id, final TenantMasterVO tenant) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<TenantMasterVO> httpEntity = new HttpEntity<>(tenant, httpHeaders);
        final String url = String.format("%s/duc/common/api/tenants/%s/update", this.getServerHost(), id);
        final ResponseEntity<DeiResponseData<Long>> response = execute(url, HttpMethod.PUT, httpEntity, new ParameterizedTypeReference<DeiResponseData<Long>>() {}, true);
        checkResponse(response);
    }

    @Override
    public Integer removeTenants(final TenantDelParam delParam) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<TenantDelParam> httpEntity = new HttpEntity<>(delParam, httpHeaders);
        final String url = String.format("%s/duc/common/api/tenants/remove", this.getServerHost());
        final ResponseEntity<DeiResponseData<Integer>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Integer>>() {}, true);
        final DeiResponseData<Integer> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public Long saveTenantUserGrp(final TenantUserGrpMasterVO userGrp) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<TenantUserGrpMasterVO> httpEntity = new HttpEntity<>(userGrp, httpHeaders);
        final String url = String.format("%s/duc/common/api/tenant-user/user-grps/save", this.getServerHost());
        final ResponseEntity<DeiResponseData<Long>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Long>>() {}, true);
        final DeiResponseData<Long> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public void updateTenantUserGrp(final Long id, final TenantUserGrpMasterVO userGrp) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<TenantUserGrpMasterVO> httpEntity = new HttpEntity<>(userGrp, httpHeaders);
        final String url = String.format("%s/duc/common/api/tenant-user/user-grps/%s/update", this.getServerHost(), id);
        final ResponseEntity<DeiResponseData<Long>> response = execute(url, HttpMethod.PUT, httpEntity, new ParameterizedTypeReference<DeiResponseData<Long>>() {}, true);
        checkResponse(response);
    }

    @Override
    public Integer removeTenantUserGrps(final TenantUserGrpDelParam delParam) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<TenantUserGrpDelParam> httpEntity = new HttpEntity<>(delParam, httpHeaders);
        final String url = String.format("%s/duc/common/api/tenant-user/user-grps/remove", this.getServerHost());
        final ResponseEntity<DeiResponseData<Integer>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Integer>>() {}, true);
        final DeiResponseData<Integer> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public Long saveTenantUser(final TenantUserMasterVO tenantUser) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<TenantUserMasterVO> httpEntity = new HttpEntity<>(tenantUser, httpHeaders);
        final String url = String.format("%s/duc/common/api/tenant-users/save", this.getServerHost());
        final ResponseEntity<DeiResponseData<Long>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Long>>() {}, true);
        final DeiResponseData<Long> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public void updateTenantUser(final Long id, final TenantUserMasterVO tenantUser) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<TenantUserMasterVO> httpEntity = new HttpEntity<>(tenantUser, httpHeaders);
        final String url = String.format("%s/duc/common/api/tenant-users/%s/update", this.getServerHost(), id);
        final ResponseEntity<DeiResponseData<Long>> response = execute(url, HttpMethod.PUT, httpEntity, new ParameterizedTypeReference<DeiResponseData<Long>>() {}, true);
        checkResponse(response);
    }

    @Override
    public Integer removeTenantUsers(final TenantUserDelParam delParam) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<TenantUserDelParam> httpEntity = new HttpEntity<>(delParam, httpHeaders);
        final String url = String.format("%s/duc/common/api/tenant-users/remove", this.getServerHost());
        final ResponseEntity<DeiResponseData<Integer>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Integer>>() {}, true);
        final DeiResponseData<Integer> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public Long saveTenantUserRole(final TenantUserRoleMasterVO role) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<TenantUserRoleMasterVO> httpEntity = new HttpEntity<>(role, httpHeaders);
        final String url = String.format("%s/duc/common/api/tenant-user/roles/save", this.getServerHost());
        final ResponseEntity<DeiResponseData<Long>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Long>>() {}, true);
        final DeiResponseData<Long> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public void updateTenantUserRole(final Long id, final TenantUserRoleMasterVO role) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<TenantUserRoleMasterVO> httpEntity = new HttpEntity<>(role, httpHeaders);
        final String url = String.format("%s/duc/common/api/tenant-user/roles/%s/update", this.getServerHost(), id);
        final ResponseEntity<DeiResponseData<Long>> response = execute(url, HttpMethod.PUT, httpEntity, new ParameterizedTypeReference<DeiResponseData<Long>>() {}, true);
        checkResponse(response);
    }

    @Override
    public Integer removeTenantUserRoles(final TenantUserRoleDelParam delParam) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<TenantUserRoleDelParam> httpEntity = new HttpEntity<>(delParam, httpHeaders);
        final String url = String.format("%s/duc/common/api/tenant-user/roles/remove", this.getServerHost());
        final ResponseEntity<DeiResponseData<Integer>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Integer>>() {}, true);
        final DeiResponseData<Integer> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public Long saveTenantUserTmpRole(final TenantUserTmpRoleMasterVO tmpRole) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<TenantUserTmpRoleMasterVO> httpEntity = new HttpEntity<>(tmpRole, httpHeaders);
        final String url = String.format("%s/duc/common/api/tenant-user/tmp-roles/save", this.getServerHost());
        final ResponseEntity<DeiResponseData<Long>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Long>>() {}, true);
        final DeiResponseData<Long> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public void updateTenantUserTmpRole(final Long id, final TenantUserTmpRoleMasterVO tmpRole) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<TenantUserTmpRoleMasterVO> httpEntity = new HttpEntity<>(tmpRole, httpHeaders);
        final String url = String.format("%s/duc/common/api/tenant-user/tmp-roles/%s/update", this.getServerHost(), id);
        final ResponseEntity<DeiResponseData<Long>> response = execute(url, HttpMethod.PUT, httpEntity, new ParameterizedTypeReference<DeiResponseData<Long>>() {}, true);
        checkResponse(response);
    }

    @Override
    public Integer removeTenantUserTmpRoles(final TenantUserTmpRoleDelParam delParam) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<TenantUserTmpRoleDelParam> httpEntity = new HttpEntity<>(delParam, httpHeaders);
        final String url = String.format("%s/duc/common/api/tenant-user/tmp-roles/remove", this.getServerHost());
        final ResponseEntity<DeiResponseData<Integer>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Integer>>() {}, true);
        final DeiResponseData<Integer> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public Long saveTenantUserSysResRole(final TenantUserSysResRoleMasterVO sysResRole) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<TenantUserSysResRoleMasterVO> httpEntity = new HttpEntity<>(sysResRole, httpHeaders);
        final String url = String.format("%s/duc/common/api/tenant-user/sys-res-roles/save", this.getServerHost());
        final ResponseEntity<DeiResponseData<Long>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Long>>() {}, true);
        final DeiResponseData<Long> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public void updateTenantUserSysResRole(final Long id, final TenantUserSysResRoleMasterVO sysResRole) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<TenantUserSysResRoleMasterVO> httpEntity = new HttpEntity<>(sysResRole, httpHeaders);
        final String url = String.format("%s/duc/common/api/tenant-user/sys-res-roles/%s/update", this.getServerHost(), id);
        final ResponseEntity<DeiResponseData<Long>> response = execute(url, HttpMethod.PUT, httpEntity, new ParameterizedTypeReference<DeiResponseData<Long>>() {}, true);
        checkResponse(response);
    }

    @Override
    public Integer removeTenantUserSysResRoles(final TenantUserSysResRoleDelParam delParam) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<TenantUserSysResRoleDelParam> httpEntity = new HttpEntity<>(delParam, httpHeaders);
        final String url = String.format("%s/duc/common/api/tenant-user/sys-res-roles/remove", this.getServerHost());
        final ResponseEntity<DeiResponseData<Integer>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Integer>>() {}, true);
        final DeiResponseData<Integer> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public Long saveTenantUserTmpSysResRole(final TenantUserTmpSysResRoleMasterVO tmpSysResRole) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<TenantUserTmpSysResRoleMasterVO> httpEntity = new HttpEntity<>(tmpSysResRole, httpHeaders);
        final String url = String.format("%s/duc/common/api/tenant-user/tmp-sys-res-roles/save", this.getServerHost());
        final ResponseEntity<DeiResponseData<Long>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Long>>() {}, true);
        final DeiResponseData<Long> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public void updateTenantUserTmpSysResRole(final Long id, final TenantUserTmpSysResRoleMasterVO tmpSysResRole) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<TenantUserTmpSysResRoleMasterVO> httpEntity = new HttpEntity<>(tmpSysResRole, httpHeaders);
        final String url = String.format("%s/duc/common/api/tenant-user/tmp-sys-res-roles/%s/update", this.getServerHost(), id);
        final ResponseEntity<DeiResponseData<Long>> response = execute(url, HttpMethod.PUT, httpEntity, new ParameterizedTypeReference<DeiResponseData<Long>>() {}, true);
        checkResponse(response);
    }

    @Override
    public Integer removeTenantUserTmpSysResRoles(final TenantUserTmpSysResRoleDelParam delParam) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<TenantUserTmpSysResRoleDelParam> httpEntity = new HttpEntity<>(delParam, httpHeaders);
        final String url = String.format("%s/duc/common/api/tenant-user/tmp-sys-res-roles/remove", this.getServerHost());
        final ResponseEntity<DeiResponseData<Integer>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Integer>>() {}, true);
        final DeiResponseData<Integer> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public Long saveEmployeeUserGrp(final EmployeeUserGrpMasterVO userGrp) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<EmployeeUserGrpMasterVO> httpEntity = new HttpEntity<>(userGrp, httpHeaders);
        final String url = String.format("%s/duc/common/api/employee/user-grps/save", this.getServerHost());
        final ResponseEntity<DeiResponseData<Long>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Long>>() {}, true);
        final DeiResponseData<Long> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public void updateEmployeeUserGrp(final Long id, final EmployeeUserGrpMasterVO userGrp) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<EmployeeUserGrpMasterVO> httpEntity = new HttpEntity<>(userGrp, httpHeaders);
        final String url = String.format("%s/duc/common/api/employee/user-grps/%s/update", this.getServerHost(), id);
        final ResponseEntity<DeiResponseData<Long>> response = execute(url, HttpMethod.PUT, httpEntity, new ParameterizedTypeReference<DeiResponseData<Long>>() {}, true);
        checkResponse(response);
    }

    @Override
    public Integer removeEmployeeUserGrps(final EmployeeUserGrpDelParam delParam) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<EmployeeUserGrpDelParam> httpEntity = new HttpEntity<>(delParam, httpHeaders);
        final String url = String.format("%s/duc/common/api/employee/user-grps/remove", this.getServerHost());
        final ResponseEntity<DeiResponseData<Integer>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Integer>>() {}, true);
        final DeiResponseData<Integer> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public Long saveEmployee(final EmployeeMasterVO employee) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<EmployeeMasterVO> httpEntity = new HttpEntity<>(employee, httpHeaders);
        final String url = String.format("%s/duc/common/api/employees/save", this.getServerHost());
        final ResponseEntity<DeiResponseData<Long>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Long>>() {}, true);
        final DeiResponseData<Long> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public void updateEmployee(final Long id, final EmployeeMasterVO employee) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<EmployeeMasterVO> httpEntity = new HttpEntity<>(employee, httpHeaders);
        final String url = String.format("%s/duc/common/api/employees/%s/update", this.getServerHost(), id);
        final ResponseEntity<DeiResponseData<Long>> response = execute(url, HttpMethod.PUT, httpEntity, new ParameterizedTypeReference<DeiResponseData<Long>>() {}, true);
        checkResponse(response);
    }

    @Override
    public Integer removeEmployees(final EmployeeDelParam delParam) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<EmployeeDelParam> httpEntity = new HttpEntity<>(delParam, httpHeaders);
        final String url = String.format("%s/duc/common/api/employees/remove", this.getServerHost());
        final ResponseEntity<DeiResponseData<Integer>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Integer>>() {}, true);
        final DeiResponseData<Integer> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public Long saveEmployeeRole(final EmployeeRoleMasterVO role) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<EmployeeRoleMasterVO> httpEntity = new HttpEntity<>(role, httpHeaders);
        final String url = String.format("%s/duc/common/api/employee/roles/save", this.getServerHost());
        final ResponseEntity<DeiResponseData<Long>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Long>>() {}, true);
        final DeiResponseData<Long> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public void updateEmployeeRole(final Long id, final EmployeeRoleMasterVO role) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<EmployeeRoleMasterVO> httpEntity = new HttpEntity<>(role, httpHeaders);
        final String url = String.format("%s/duc/common/api/employee/roles/%s/update", this.getServerHost(), id);
        final ResponseEntity<DeiResponseData<Long>> response = execute(url, HttpMethod.PUT, httpEntity, new ParameterizedTypeReference<DeiResponseData<Long>>() {}, true);
        checkResponse(response);
    }

    @Override
    public Integer removeEmployeeRoles(final EmployeeRoleDelParam delParam) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<EmployeeRoleDelParam> httpEntity = new HttpEntity<>(delParam, httpHeaders);
        final String url = String.format("%s/duc/common/api/employee/roles/remove", this.getServerHost());
        final ResponseEntity<DeiResponseData<Integer>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Integer>>() {}, true);
        final DeiResponseData<Integer> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public Long saveEmployeeTmpRole(final EmployeeTmpRoleMasterVO tmpRole) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<EmployeeTmpRoleMasterVO> httpEntity = new HttpEntity<>(tmpRole, httpHeaders);
        final String url = String.format("%s/duc/common/api/employee/tmp-roles/save", this.getServerHost());
        final ResponseEntity<DeiResponseData<Long>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Long>>() {}, true);
        final DeiResponseData<Long> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public void updateEmployeeTmpRole(final Long id, final EmployeeTmpRoleMasterVO tmpRole) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<EmployeeTmpRoleMasterVO> httpEntity = new HttpEntity<>(tmpRole, httpHeaders);
        final String url = String.format("%s/duc/common/api/employee/tmp-roles/%s/update", this.getServerHost(), id);
        final ResponseEntity<DeiResponseData<Long>> response = execute(url, HttpMethod.PUT, httpEntity, new ParameterizedTypeReference<DeiResponseData<Long>>() {}, true);
        checkResponse(response);
    }

    @Override
    public Integer removeEmployeeTmpRoles(final EmployeeTmpRoleDelParam delParam) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<EmployeeTmpRoleDelParam> httpEntity = new HttpEntity<>(delParam, httpHeaders);
        final String url = String.format("%s/duc/common/api/employee/tmp-roles/remove", this.getServerHost());
        final ResponseEntity<DeiResponseData<Integer>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Integer>>() {}, true);
        final DeiResponseData<Integer> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public Long saveEmployeeSysResRole(final EmployeeSysResRoleMasterVO sysResRole) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<EmployeeSysResRoleMasterVO> httpEntity = new HttpEntity<>(sysResRole, httpHeaders);
        final String url = String.format("%s/duc/common/api/employee/sys-res-roles/save", this.getServerHost());
        final ResponseEntity<DeiResponseData<Long>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Long>>() {}, true);
        final DeiResponseData<Long> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public void updateEmployeeSysResRole(final Long id, final EmployeeSysResRoleMasterVO sysResRole) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<EmployeeSysResRoleMasterVO> httpEntity = new HttpEntity<>(sysResRole, httpHeaders);
        final String url = String.format("%s/duc/common/api/employee/sys-res-roles/%s/update", this.getServerHost(), id);
        final ResponseEntity<DeiResponseData<Long>> response = execute(url, HttpMethod.PUT, httpEntity, new ParameterizedTypeReference<DeiResponseData<Long>>() {}, true);
        checkResponse(response);
    }

    @Override
    public Integer removeEmployeeSysResRoles(final EmployeeSysResRoleDelParam delParam) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<EmployeeSysResRoleDelParam> httpEntity = new HttpEntity<>(delParam, httpHeaders);
        final String url = String.format("%s/duc/common/api/employee/sys-res-roles/remove", this.getServerHost());
        final ResponseEntity<DeiResponseData<Integer>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Integer>>() {}, true);
        final DeiResponseData<Integer> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public Long saveEmployeeTmpSysResRole(final EmployeeTmpSysResRoleMasterVO tmpSysResRole) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<EmployeeTmpSysResRoleMasterVO> httpEntity = new HttpEntity<>(tmpSysResRole, httpHeaders);
        final String url = String.format("%s/duc/common/api/employee/tmp-sys-res-roles/save", this.getServerHost());
        final ResponseEntity<DeiResponseData<Long>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Long>>() {}, true);
        final DeiResponseData<Long> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public void updateEmployeeTmpSysResRole(final Long id, final EmployeeTmpSysResRoleMasterVO tmpSysResRole) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<EmployeeTmpSysResRoleMasterVO> httpEntity = new HttpEntity<>(tmpSysResRole, httpHeaders);
        final String url = String.format("%s/duc/common/api/employee/tmp-sys-res-roles/%s/update", this.getServerHost(), id);
        final ResponseEntity<DeiResponseData<Long>> response = execute(url, HttpMethod.PUT, httpEntity, new ParameterizedTypeReference<DeiResponseData<Long>>() {}, true);
        checkResponse(response);
    }

    @Override
    public Integer removeEmployeeTmpSysResRoles(final EmployeeTmpSysResRoleDelParam delParam) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<EmployeeTmpSysResRoleDelParam> httpEntity = new HttpEntity<>(delParam, httpHeaders);
        final String url = String.format("%s/duc/common/api/employee/tmp-sys-res-roles/remove", this.getServerHost());
        final ResponseEntity<DeiResponseData<Integer>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Integer>>() {}, true);
        final DeiResponseData<Integer> result = checkResponse(response);
        return result.getData();
    }
}
