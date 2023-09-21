package com.ioiox.dei.duc.std.data.svc.impl.master.tenant;

import com.ioiox.dei.duc.beans.entity.TenantUserRole;
import com.ioiox.dei.duc.beans.model.master.tenant.TenantUserRoleDelParam;
import com.ioiox.dei.duc.beans.model.master.tenant.TenantUserRoleUpdatableObj;
import com.ioiox.dei.duc.beans.model.master.tenant.TenantUserRoleUpdateCtx;
import com.ioiox.dei.duc.beans.vo.std.master.tenant.TenantUserRoleMasterVO;
import com.ioiox.dei.duc.beans.vo.std.slave.tenant.TenantUserRoleSlaveVO;
import com.ioiox.dei.duc.db.service.master.tenant.TenantUserRoleMasterDbSvc;
import com.ioiox.dei.duc.std.data.svc.impl.master.BaseRoleMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.master.tenant.TenantUserRoleMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.slave.tenant.TenantUserRoleSlaveStdDataSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("tenantUserRoleMasterStdDataSvc")
public class TenantUserRoleMasterStdDataSvcImpl
        extends BaseRoleMasterStdDataSvc<TenantUserRoleMasterVO, TenantUserRoleUpdatableObj, TenantUserRoleUpdateCtx, TenantUserRoleDelParam, TenantUserRoleSlaveVO, TenantUserRole>
        implements TenantUserRoleMasterStdDataSvc {

    @Autowired
    @Qualifier("tenantUserRoleMasterDbSvc")
    private TenantUserRoleMasterDbSvc tenantUserRoleMasterDbSvc;

    @Autowired
    @Qualifier("tenantUserRoleSlaveStdDataSvc")
    private TenantUserRoleSlaveStdDataSvc tenantUserRoleSlaveStdDataSvc;

    @Override
    protected TenantUserRoleSlaveVO getExistingRole(final Long id) {
        return null;
    }

    @Override
    protected List<TenantUserRoleSlaveVO> queryExistingRoles(final TenantUserRoleDelParam delParam) {
        return null;
    }

    @Override
    protected TenantUserRoleUpdateCtx getUpdateContext(TenantUserRoleMasterVO role, TenantUserRoleSlaveVO existingRole) {
        return null;
    }

    @Override
    protected void doSave(TenantUserRole newEntity) {

    }

    @Override
    protected int doUpdate(TenantUserRole example) {
        return 0;
    }

    @Override
    protected int doRemove(Map<String, Object> deleteParams) {
        return 0;
    }

    @Override
    protected int assignMenusToRole(List<Long> menuIds, Long roleId, String operator) {
        return 0;
    }

    @Override
    protected int removeMenusFromRole(List<Long> menuIds, Long roleId, String operator) {
        return 0;
    }

    @Override
    protected int removeMenusFromRoles(List<Long> roleIds) {
        return 0;
    }

    @Override
    protected int assignMenuSysApisToRole(List<Long> sysApiMappingIds, Long roleId, String operator) {
        return 0;
    }

    @Override
    protected int removeMenuSysApisFromRole(List<Long> sysApiMappingIds, Long roleId, String operator) {
        return 0;
    }

    @Override
    protected int removeMenuSysApisFromRoles(List<Long> roleIds) {
        return 0;
    }

    @Override
    protected int assignSysApisToRole(List<Long> sysApiIds, Long roleId, String operator) {
        return 0;
    }

    @Override
    protected int removeSysApisFromRole(List<Long> sysApiIds, Long roleId, String operator) {
        return 0;
    }

    @Override
    protected int removeSysApisFromRoles(List<Long> roleIds) {
        return 0;
    }

    @Override
    public TenantUserRole toNewEntity(TenantUserRoleMasterVO tenantUserRoleMasterVO) {
        return null;
    }

    @Override
    public TenantUserRole toUpdatableObj(TenantUserRoleUpdatableObj tenantUserRoleUpdatableObj) {
        return null;
    }
}
