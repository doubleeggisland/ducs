package com.ioiox.dei.rest.api.clients;


import com.ioiox.dei.core.orm.mybatis.model.std.data.StdDataQueryCfg;
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
import com.ioiox.dei.duc.beans.model.slave.*;
import com.ioiox.dei.duc.beans.model.slave.employee.*;
import com.ioiox.dei.duc.beans.model.slave.tenant.*;
import com.ioiox.dei.duc.beans.model.slave.user.*;
import com.ioiox.dei.duc.beans.vo.std.master.MenuMasterVO;
import com.ioiox.dei.duc.beans.vo.std.master.SysApiMasterVO;
import com.ioiox.dei.duc.beans.vo.std.master.SysPrjMasterVO;
import com.ioiox.dei.duc.beans.vo.std.master.SysResMasterVO;
import com.ioiox.dei.duc.beans.vo.std.master.employee.*;
import com.ioiox.dei.duc.beans.vo.std.master.tenant.*;
import com.ioiox.dei.duc.beans.vo.std.master.user.*;
import com.ioiox.dei.duc.beans.vo.std.slave.MenuSlaveVO;
import com.ioiox.dei.duc.beans.vo.std.slave.SysApiSlaveVO;
import com.ioiox.dei.duc.beans.vo.std.slave.SysPrjSlaveVO;
import com.ioiox.dei.duc.beans.vo.std.slave.SysResSlaveVO;
import com.ioiox.dei.duc.beans.vo.std.slave.employee.*;
import com.ioiox.dei.duc.beans.vo.std.slave.tenant.*;
import com.ioiox.dei.duc.beans.vo.std.slave.user.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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

    @Override
    public Integer countMenus(final MenuQueryParam queryParam) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<MenuQueryParam> httpEntity = new HttpEntity<>(queryParam, httpHeaders);
        final String url = String.format("%s/duc/common/api/menus/count", this.getServerHost());
        final ResponseEntity<DeiResponseData<Integer>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Integer>>() {}, true);
        final DeiResponseData<Integer> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public List<MenuSlaveVO> queryMenus(final MenuQueryParam queryParam, final MenuQueryCfg queryCfg) {
        final MenuQueryReq queryReq = new MenuQueryReq(queryParam, queryCfg);
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<MenuQueryReq> httpEntity = new HttpEntity<>(queryReq, httpHeaders);
        final String url = String.format("%s/duc/common/api/menus/query", this.getServerHost());
        final ResponseEntity<DeiResponseData<List<MenuSlaveVO>>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<List<MenuSlaveVO>>>() {}, true);
        final DeiResponseData<List<MenuSlaveVO>> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public Integer countSysApis(final SysApiQueryParam queryParam) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<SysApiQueryParam> httpEntity = new HttpEntity<>(queryParam, httpHeaders);
        final String url = String.format("%s/duc/common/api/sys-apis/count", this.getServerHost());
        final ResponseEntity<DeiResponseData<Integer>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Integer>>() {}, true);
        final DeiResponseData<Integer> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public List<SysApiSlaveVO> querySysApis(final SysApiQueryParam queryParam, final StdDataQueryCfg queryCfg) {
        final SysApiQueryReq queryReq = new SysApiQueryReq(queryParam, queryCfg);
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<SysApiQueryReq> httpEntity = new HttpEntity<>(queryReq, httpHeaders);
        final String url = String.format("%s/duc/common/api/sys-apis/query", this.getServerHost());
        final ResponseEntity<DeiResponseData<List<SysApiSlaveVO>>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<List<SysApiSlaveVO>>>() {}, true);
        final DeiResponseData<List<SysApiSlaveVO>> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public Integer countSysPrjs(final SysPrjQueryParam queryParam) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<SysPrjQueryParam> httpEntity = new HttpEntity<>(queryParam, httpHeaders);
        final String url = String.format("%s/duc/common/api/sys-prjs/count", this.getServerHost());
        final ResponseEntity<DeiResponseData<Integer>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Integer>>() {}, true);
        final DeiResponseData<Integer> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public List<SysPrjSlaveVO> querySysPrjs(final SysPrjQueryParam queryParam, final SysPrjQueryCfg queryCfg) {
        final SysPrjQueryReq queryReq = new SysPrjQueryReq(queryParam, queryCfg);
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<SysPrjQueryReq> httpEntity = new HttpEntity<>(queryReq, httpHeaders);
        final String url = String.format("%s/duc/common/api/sys-prjs/query", this.getServerHost());
        final ResponseEntity<DeiResponseData<List<SysPrjSlaveVO>>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<List<SysPrjSlaveVO>>>() {}, true);
        final DeiResponseData<List<SysPrjSlaveVO>> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public Integer countSysResources(final SysResQueryParam queryParam) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<SysResQueryParam> httpEntity = new HttpEntity<>(queryParam, httpHeaders);
        final String url = String.format("%s/duc/common/api/sys-resources/count", this.getServerHost());
        final ResponseEntity<DeiResponseData<Integer>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Integer>>() {}, true);
        final DeiResponseData<Integer> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public List<SysResSlaveVO> querySysResources(final SysResQueryParam queryParam, final StdDataQueryCfg queryCfg) {
        final SysResQueryReq queryReq = new SysResQueryReq(queryParam, queryCfg);
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<SysResQueryReq> httpEntity = new HttpEntity<>(queryReq, httpHeaders);
        final String url = String.format("%s/duc/common/api/sys-resources/query", this.getServerHost());
        final ResponseEntity<DeiResponseData<List<SysResSlaveVO>>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<List<SysResSlaveVO>>>() {}, true);
        final DeiResponseData<List<SysResSlaveVO>> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public Integer countAcctUserGrps(final AcctUserGrpQueryParam queryParam) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<AcctUserGrpQueryParam> httpEntity = new HttpEntity<>(queryParam, httpHeaders);
        final String url = String.format("%s/duc/common/api/user-acct/user-grps/count", this.getServerHost());
        final ResponseEntity<DeiResponseData<Integer>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Integer>>() {}, true);
        final DeiResponseData<Integer> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public List<AcctUserGrpSlaveVO> queryAcctUserGrps(final AcctUserGrpQueryParam queryParam, final UserGrpQueryCfg queryCfg) {
        final AcctUserGrpQueryReq queryReq = new AcctUserGrpQueryReq(queryParam, queryCfg);
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<AcctUserGrpQueryReq> httpEntity = new HttpEntity<>(queryReq, httpHeaders);
        final String url = String.format("%s/duc/common/api/user-acct/user-grps/query", this.getServerHost());
        final ResponseEntity<DeiResponseData<List<AcctUserGrpSlaveVO>>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<List<AcctUserGrpSlaveVO>>>() {}, true);
        final DeiResponseData<List<AcctUserGrpSlaveVO>> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public Integer countUserAccts(final UserAcctQueryParam queryParam) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<UserAcctQueryParam> httpEntity = new HttpEntity<>(queryParam, httpHeaders);
        final String url = String.format("%s/duc/common/api/user-accts/count", this.getServerHost());
        final ResponseEntity<DeiResponseData<Integer>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Integer>>() {}, true);
        final DeiResponseData<Integer> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public List<UserAcctSlaveVO> queryUserAccts(final UserAcctQueryParam queryParam, final UserQueryCfg queryCfg) {
        final UserAcctQueryReq queryReq = new UserAcctQueryReq(queryParam, queryCfg);
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<UserAcctQueryReq> httpEntity = new HttpEntity<>(queryReq, httpHeaders);
        final String url = String.format("%s/duc/common/api/user-accts/query", this.getServerHost());
        final ResponseEntity<DeiResponseData<List<UserAcctSlaveVO>>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<List<UserAcctSlaveVO>>>() {}, true);
        final DeiResponseData<List<UserAcctSlaveVO>> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public Integer countUserAcctRoles(final UserAcctRoleQueryParam queryParam) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<UserAcctRoleQueryParam> httpEntity = new HttpEntity<>(queryParam, httpHeaders);
        final String url = String.format("%s/duc/common/api/user-acct/roles/count", this.getServerHost());
        final ResponseEntity<DeiResponseData<Integer>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Integer>>() {}, true);
        final DeiResponseData<Integer> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public List<UserAcctRoleSlaveVO> queryUserAcctRoles(final UserAcctRoleQueryParam queryParam, final RoleQueryCfg queryCfg) {
        final UserAcctRoleQueryReq queryReq = new UserAcctRoleQueryReq(queryParam, queryCfg);
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<UserAcctRoleQueryReq> httpEntity = new HttpEntity<>(queryReq, httpHeaders);
        final String url = String.format("%s/duc/common/api/user-acct/roles/query", this.getServerHost());
        final ResponseEntity<DeiResponseData<List<UserAcctRoleSlaveVO>>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<List<UserAcctRoleSlaveVO>>>() {}, true);
        final DeiResponseData<List<UserAcctRoleSlaveVO>> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public Integer countUserAcctTmpRoles(final UserAcctTmpRoleQueryParam queryParam) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<UserAcctTmpRoleQueryParam> httpEntity = new HttpEntity<>(queryParam, httpHeaders);
        final String url = String.format("%s/duc/common/api/user-acct/tmp-roles/count", this.getServerHost());
        final ResponseEntity<DeiResponseData<Integer>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Integer>>() {}, true);
        final DeiResponseData<Integer> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public List<UserAcctTmpRoleSlaveVO> queryUserAcctTmpRoles(final UserAcctTmpRoleQueryParam queryParam, final RoleQueryCfg queryCfg) {
        final UserAcctTmpRoleQueryReq queryReq = new UserAcctTmpRoleQueryReq(queryParam, queryCfg);
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<UserAcctTmpRoleQueryReq> httpEntity = new HttpEntity<>(queryReq, httpHeaders);
        final String url = String.format("%s/duc/common/api/user-acct/tmp-roles/query", this.getServerHost());
        final ResponseEntity<DeiResponseData<List<UserAcctTmpRoleSlaveVO>>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<List<UserAcctTmpRoleSlaveVO>>>() {}, true);
        final DeiResponseData<List<UserAcctTmpRoleSlaveVO>> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public Integer countUserAcctSysResRoles(final UserAcctSysResRoleQueryParam queryParam) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<UserAcctSysResRoleQueryParam> httpEntity = new HttpEntity<>(queryParam, httpHeaders);
        final String url = String.format("%s/duc/common/api/user-acct/sys-res-roles/count", this.getServerHost());
        final ResponseEntity<DeiResponseData<Integer>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Integer>>() {}, true);
        final DeiResponseData<Integer> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public List<UserAcctSysResRoleSlaveVO> queryUserAcctSysResRoles(final UserAcctSysResRoleQueryParam queryParam, final SysResRoleQueryCfg queryCfg) {
        final UserAcctSysResRoleQueryReq queryReq = new UserAcctSysResRoleQueryReq(queryParam, queryCfg);
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<UserAcctSysResRoleQueryReq> httpEntity = new HttpEntity<>(queryReq, httpHeaders);
        final String url = String.format("%s/duc/common/api/user-acct/sys-res-roles/query", this.getServerHost());
        final ResponseEntity<DeiResponseData<List<UserAcctSysResRoleSlaveVO>>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<List<UserAcctSysResRoleSlaveVO>>>() {}, true);
        final DeiResponseData<List<UserAcctSysResRoleSlaveVO>> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public Integer countUserAcctTmpSysResRoles(final UserAcctTmpSysResRoleQueryParam queryParam) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<UserAcctTmpSysResRoleQueryParam> httpEntity = new HttpEntity<>(queryParam, httpHeaders);
        final String url = String.format("%s/duc/common/api/user-acct/tmp-sys-res-roles/count", this.getServerHost());
        final ResponseEntity<DeiResponseData<Integer>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Integer>>() {}, true);
        final DeiResponseData<Integer> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public List<UserAcctTmpSysResRoleSlaveVO> queryUserAcctTmpSysResRoles(final UserAcctTmpSysResRoleQueryParam queryParam, final SysResRoleQueryCfg queryCfg) {
        final UserAcctTmpSysResRoleQueryReq queryReq = new UserAcctTmpSysResRoleQueryReq(queryParam, queryCfg);
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<UserAcctTmpSysResRoleQueryReq> httpEntity = new HttpEntity<>(queryReq, httpHeaders);
        final String url = String.format("%s/duc/common/api/user-acct/tmp-sys-res-roles/query", this.getServerHost());
        final ResponseEntity<DeiResponseData<List<UserAcctTmpSysResRoleSlaveVO>>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<List<UserAcctTmpSysResRoleSlaveVO>>>() {}, true);
        final DeiResponseData<List<UserAcctTmpSysResRoleSlaveVO>> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public Integer countTenants(final TenantQueryParam queryParam) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<TenantQueryParam> httpEntity = new HttpEntity<>(queryParam, httpHeaders);
        final String url = String.format("%s/duc/common/api/tenants/count", this.getServerHost());
        final ResponseEntity<DeiResponseData<Integer>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Integer>>() {}, true);
        final DeiResponseData<Integer> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public List<TenantSlaveVO> queryTenants(final TenantQueryParam queryParam, final TenantQueryCfg queryCfg) {
        final TenantQueryReq queryReq = new TenantQueryReq(queryParam, queryCfg);
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<TenantQueryReq> httpEntity = new HttpEntity<>(queryReq, httpHeaders);
        final String url = String.format("%s/duc/common/api/tenants/query", this.getServerHost());
        final ResponseEntity<DeiResponseData<List<TenantSlaveVO>>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<List<TenantSlaveVO>>>() {}, true);
        final DeiResponseData<List<TenantSlaveVO>> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public Integer countTenantUserGrps(final TenantUserGrpQueryParam queryParam) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<TenantUserGrpQueryParam> httpEntity = new HttpEntity<>(queryParam, httpHeaders);
        final String url = String.format("%s/duc/common/api/tenant-user/user-grps/count", this.getServerHost());
        final ResponseEntity<DeiResponseData<Integer>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Integer>>() {}, true);
        final DeiResponseData<Integer> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public List<TenantUserGrpSlaveVO> queryTenantUserGrps(final TenantUserGrpQueryParam queryParam, final UserGrpQueryCfg queryCfg) {
        final TenantUserGrpQueryReq queryReq = new TenantUserGrpQueryReq(queryParam, queryCfg);
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<TenantUserGrpQueryReq> httpEntity = new HttpEntity<>(queryReq, httpHeaders);
        final String url = String.format("%s/duc/common/api/tenant-user/user-grps/query", this.getServerHost());
        final ResponseEntity<DeiResponseData<List<TenantUserGrpSlaveVO>>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<List<TenantUserGrpSlaveVO>>>() {}, true);
        final DeiResponseData<List<TenantUserGrpSlaveVO>> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public Integer countTenantUsers(final TenantUserQueryParam queryParam) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<TenantUserQueryParam> httpEntity = new HttpEntity<>(queryParam, httpHeaders);
        final String url = String.format("%s/duc/common/api/tenant-users/count", this.getServerHost());
        final ResponseEntity<DeiResponseData<Integer>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Integer>>() {}, true);
        final DeiResponseData<Integer> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public List<TenantUserSlaveVO> queryTenantUsers(final TenantUserQueryParam queryParam, final UserQueryCfg queryCfg) {
        final TenantUserQueryReq queryReq = new TenantUserQueryReq(queryParam, queryCfg);
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<TenantUserQueryReq> httpEntity = new HttpEntity<>(queryReq, httpHeaders);
        final String url = String.format("%s/duc/common/api/tenant-users/query", this.getServerHost());
        final ResponseEntity<DeiResponseData<List<TenantUserSlaveVO>>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<List<TenantUserSlaveVO>>>() {}, true);
        final DeiResponseData<List<TenantUserSlaveVO>> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public Integer countTenantUserRoles(final TenantUserRoleQueryParam queryParam) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<TenantUserRoleQueryParam> httpEntity = new HttpEntity<>(queryParam, httpHeaders);
        final String url = String.format("%s/duc/common/api/tenant-user/roles/count", this.getServerHost());
        final ResponseEntity<DeiResponseData<Integer>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Integer>>() {}, true);
        final DeiResponseData<Integer> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public List<TenantUserRoleSlaveVO> queryTenantUserRoles(final TenantUserRoleQueryParam queryParam, final RoleQueryCfg queryCfg) {
        final TenantUserRoleQueryReq queryReq = new TenantUserRoleQueryReq(queryParam, queryCfg);
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<TenantUserRoleQueryReq> httpEntity = new HttpEntity<>(queryReq, httpHeaders);
        final String url = String.format("%s/duc/common/api/tenant-user/roles/query", this.getServerHost());
        final ResponseEntity<DeiResponseData<List<TenantUserRoleSlaveVO>>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<List<TenantUserRoleSlaveVO>>>() {}, true);
        final DeiResponseData<List<TenantUserRoleSlaveVO>> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public Integer countTenantUserTmpRoles(final TenantUserTmpRoleQueryParam queryParam) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<TenantUserTmpRoleQueryParam> httpEntity = new HttpEntity<>(queryParam, httpHeaders);
        final String url = String.format("%s/duc/common/api/tenant-user/tmp-roles/count", this.getServerHost());
        final ResponseEntity<DeiResponseData<Integer>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Integer>>() {}, true);
        final DeiResponseData<Integer> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public List<TenantUserTmpRoleSlaveVO> queryTenantUserTmpRoles(final TenantUserTmpRoleQueryParam queryParam, final RoleQueryCfg queryCfg) {
        final TenantUserTmpRoleQueryReq queryReq = new TenantUserTmpRoleQueryReq(queryParam, queryCfg);
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<TenantUserTmpRoleQueryReq> httpEntity = new HttpEntity<>(queryReq, httpHeaders);
        final String url = String.format("%s/duc/common/api/tenant-user/tmp-roles/query", this.getServerHost());
        final ResponseEntity<DeiResponseData<List<TenantUserTmpRoleSlaveVO>>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<List<TenantUserTmpRoleSlaveVO>>>() {}, true);
        final DeiResponseData<List<TenantUserTmpRoleSlaveVO>> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public Integer countTenantUserSysResRoles(final TenantUserSysResRoleQueryParam queryParam) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<TenantUserSysResRoleQueryParam> httpEntity = new HttpEntity<>(queryParam, httpHeaders);
        final String url = String.format("%s/duc/common/api/tenant-user/sys-res-roles/count", this.getServerHost());
        final ResponseEntity<DeiResponseData<Integer>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Integer>>() {}, true);
        final DeiResponseData<Integer> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public List<TenantUserSysResRoleSlaveVO> queryTenantUserSysResRoles(final TenantUserSysResRoleQueryParam queryParam, final SysResRoleQueryCfg queryCfg) {
        final TenantUserSysResRoleQueryReq queryReq = new TenantUserSysResRoleQueryReq(queryParam, queryCfg);
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<TenantUserSysResRoleQueryReq> httpEntity = new HttpEntity<>(queryReq, httpHeaders);
        final String url = String.format("%s/duc/common/api/tenant-user/sys-res-roles/query", this.getServerHost());
        final ResponseEntity<DeiResponseData<List<TenantUserSysResRoleSlaveVO>>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<List<TenantUserSysResRoleSlaveVO>>>() {}, true);
        final DeiResponseData<List<TenantUserSysResRoleSlaveVO>> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public Integer countTenantUserTmpSysResRoles(final TenantUserTmpSysResRoleQueryParam queryParam) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<TenantUserTmpSysResRoleQueryParam> httpEntity = new HttpEntity<>(queryParam, httpHeaders);
        final String url = String.format("%s/duc/common/api/tenant-user/tmp-sys-res-roles/count", this.getServerHost());
        final ResponseEntity<DeiResponseData<Integer>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Integer>>() {}, true);
        final DeiResponseData<Integer> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public List<TenantUserTmpSysResRoleSlaveVO> queryTenantUserTmpSysResRoles(final TenantUserTmpSysResRoleQueryParam queryParam, final SysResRoleQueryCfg queryCfg) {
        final TenantUserTmpSysResRoleQueryReq queryReq = new TenantUserTmpSysResRoleQueryReq(queryParam, queryCfg);
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<TenantUserTmpSysResRoleQueryReq> httpEntity = new HttpEntity<>(queryReq, httpHeaders);
        final String url = String.format("%s/duc/common/api/tenant-user/tmp-sys-res-roles/query", this.getServerHost());
        final ResponseEntity<DeiResponseData<List<TenantUserTmpSysResRoleSlaveVO>>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<List<TenantUserTmpSysResRoleSlaveVO>>>() {}, true);
        final DeiResponseData<List<TenantUserTmpSysResRoleSlaveVO>> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public Integer countEmployeeUserGrps(final EmployeeUserGrpQueryParam queryParam) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<EmployeeUserGrpQueryParam> httpEntity = new HttpEntity<>(queryParam, httpHeaders);
        final String url = String.format("%s/duc/common/api/employee/user-grps/count", this.getServerHost());
        final ResponseEntity<DeiResponseData<Integer>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Integer>>() {}, true);
        final DeiResponseData<Integer> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public List<EmployeeUserGrpSlaveVO> queryEmployeeUserGrps(final EmployeeUserGrpQueryParam queryParam, final UserGrpQueryCfg queryCfg) {
        final EmployeeUserGrpQueryReq queryReq = new EmployeeUserGrpQueryReq(queryParam, queryCfg);
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<EmployeeUserGrpQueryReq> httpEntity = new HttpEntity<>(queryReq, httpHeaders);
        final String url = String.format("%s/duc/common/api/employee/user-grps/query", this.getServerHost());
        final ResponseEntity<DeiResponseData<List<EmployeeUserGrpSlaveVO>>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<List<EmployeeUserGrpSlaveVO>>>() {}, true);
        final DeiResponseData<List<EmployeeUserGrpSlaveVO>> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public Integer countEmployees(final EmployeeQueryParam queryParam) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<EmployeeQueryParam> httpEntity = new HttpEntity<>(queryParam, httpHeaders);
        final String url = String.format("%s/duc/common/api/employees/count", this.getServerHost());
        final ResponseEntity<DeiResponseData<Integer>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Integer>>() {}, true);
        final DeiResponseData<Integer> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public List<EmployeeSlaveVO> queryEmployees(final EmployeeQueryParam queryParam, final UserQueryCfg queryCfg) {
        final EmployeeQueryReq queryReq = new EmployeeQueryReq(queryParam, queryCfg);
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<EmployeeQueryReq> httpEntity = new HttpEntity<>(queryReq, httpHeaders);
        final String url = String.format("%s/duc/common/api/employees/query", this.getServerHost());
        final ResponseEntity<DeiResponseData<List<EmployeeSlaveVO>>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<List<EmployeeSlaveVO>>>() {}, true);
        final DeiResponseData<List<EmployeeSlaveVO>> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public Integer countEmployeeRoles(final EmployeeRoleQueryParam queryParam) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<EmployeeRoleQueryParam> httpEntity = new HttpEntity<>(queryParam, httpHeaders);
        final String url = String.format("%s/duc/common/api/employee/roles/count", this.getServerHost());
        final ResponseEntity<DeiResponseData<Integer>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Integer>>() {}, true);
        final DeiResponseData<Integer> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public List<EmployeeRoleSlaveVO> queryEmployeeRoles(final EmployeeRoleQueryParam queryParam, final RoleQueryCfg queryCfg) {
        final EmployeeRoleQueryReq queryReq = new EmployeeRoleQueryReq(queryParam, queryCfg);
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<EmployeeRoleQueryReq> httpEntity = new HttpEntity<>(queryReq, httpHeaders);
        final String url = String.format("%s/duc/common/api/employee/roles/query", this.getServerHost());
        final ResponseEntity<DeiResponseData<List<EmployeeRoleSlaveVO>>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<List<EmployeeRoleSlaveVO>>>() {}, true);
        final DeiResponseData<List<EmployeeRoleSlaveVO>> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public Integer countEmployeeTmpRoles(final EmployeeTmpRoleQueryParam queryParam) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<EmployeeTmpRoleQueryParam> httpEntity = new HttpEntity<>(queryParam, httpHeaders);
        final String url = String.format("%s/duc/common/api/employee/tmp-roles/count", this.getServerHost());
        final ResponseEntity<DeiResponseData<Integer>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Integer>>() {}, true);
        final DeiResponseData<Integer> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public List<EmployeeTmpRoleSlaveVO> queryEmployeeTmpRoles(final EmployeeTmpRoleQueryParam queryParam, final RoleQueryCfg queryCfg) {
        final EmployeeTmpRoleQueryReq queryReq = new EmployeeTmpRoleQueryReq(queryParam, queryCfg);
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<EmployeeTmpRoleQueryReq> httpEntity = new HttpEntity<>(queryReq, httpHeaders);
        final String url = String.format("%s/duc/common/api/employee/tmp-roles/query", this.getServerHost());
        final ResponseEntity<DeiResponseData<List<EmployeeTmpRoleSlaveVO>>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<List<EmployeeTmpRoleSlaveVO>>>() {}, true);
        final DeiResponseData<List<EmployeeTmpRoleSlaveVO>> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public Integer countEmployeeSysResRoles(final EmployeeSysResRoleQueryParam queryParam) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<EmployeeSysResRoleQueryParam> httpEntity = new HttpEntity<>(queryParam, httpHeaders);
        final String url = String.format("%s/duc/common/api/employee/sys-res-roles/count", this.getServerHost());
        final ResponseEntity<DeiResponseData<Integer>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Integer>>() {}, true);
        final DeiResponseData<Integer> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public List<EmployeeSysResRoleSlaveVO> queryEmployeeSysResRoles(final EmployeeSysResRoleQueryParam queryParam, final SysResRoleQueryCfg queryCfg) {
        final EmployeeSysResRoleQueryReq queryReq = new EmployeeSysResRoleQueryReq(queryParam, queryCfg);
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<EmployeeSysResRoleQueryReq> httpEntity = new HttpEntity<>(queryReq, httpHeaders);
        final String url = String.format("%s/duc/common/api/employee/sys-res-roles/query", this.getServerHost());
        final ResponseEntity<DeiResponseData<List<EmployeeSysResRoleSlaveVO>>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<List<EmployeeSysResRoleSlaveVO>>>() {}, true);
        final DeiResponseData<List<EmployeeSysResRoleSlaveVO>> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public Integer countEmployeeTmpSysResRoles(final EmployeeTmpSysResRoleQueryParam queryParam) {
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<EmployeeTmpSysResRoleQueryParam> httpEntity = new HttpEntity<>(queryParam, httpHeaders);
        final String url = String.format("%s/duc/common/api/employee/tmp-sys-res-roles/count", this.getServerHost());
        final ResponseEntity<DeiResponseData<Integer>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<Integer>>() {}, true);
        final DeiResponseData<Integer> result = checkResponse(response);
        return result.getData();
    }

    @Override
    public List<EmployeeTmpSysResRoleSlaveVO> queryEmployeeTmpSysResRoles(final EmployeeTmpSysResRoleQueryParam queryParam, final SysResRoleQueryCfg queryCfg) {
        final EmployeeTmpSysResRoleQueryReq queryReq = new EmployeeTmpSysResRoleQueryReq(queryParam, queryCfg);
        final HttpHeaders httpHeaders = buildCommonHeaders();
        final HttpEntity<EmployeeTmpSysResRoleQueryReq> httpEntity = new HttpEntity<>(queryReq, httpHeaders);
        final String url = String.format("%s/duc/common/api/employee/tmp-sys-res-roles/query", this.getServerHost());
        final ResponseEntity<DeiResponseData<List<EmployeeTmpSysResRoleSlaveVO>>> response = execute(url, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<DeiResponseData<List<EmployeeTmpSysResRoleSlaveVO>>>() {}, true);
        final DeiResponseData<List<EmployeeTmpSysResRoleSlaveVO>> result = checkResponse(response);
        return result.getData();
    }
}