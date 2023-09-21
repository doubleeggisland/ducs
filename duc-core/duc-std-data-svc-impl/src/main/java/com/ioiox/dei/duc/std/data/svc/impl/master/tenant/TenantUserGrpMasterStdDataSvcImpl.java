package com.ioiox.dei.duc.std.data.svc.impl.master.tenant;

import com.ioiox.dei.duc.beans.entity.TenantUserGrp;
import com.ioiox.dei.duc.beans.model.master.tenant.TenantUserGrpDelParam;
import com.ioiox.dei.duc.beans.model.master.tenant.TenantUserGrpUpdatableObj;
import com.ioiox.dei.duc.beans.model.master.tenant.TenantUserGrpUpdateCtx;
import com.ioiox.dei.duc.beans.vo.std.master.tenant.TenantUserGrpMasterVO;
import com.ioiox.dei.duc.beans.vo.std.slave.tenant.TenantUserGrpSlaveVO;
import com.ioiox.dei.duc.std.data.svc.impl.master.BaseUserGrpMasterStdDataSvc;
import com.ioiox.dei.duc.std.data.svc.master.tenant.TenantUserGrpMasterStdDataSvc;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("tenantUserGrpMasterStdDataSvc")
public class TenantUserGrpMasterStdDataSvcImpl
        extends BaseUserGrpMasterStdDataSvc<TenantUserGrpMasterVO, TenantUserGrpUpdatableObj, TenantUserGrpUpdateCtx, TenantUserGrpDelParam, TenantUserGrpSlaveVO, TenantUserGrp>
        implements TenantUserGrpMasterStdDataSvc {

    @Override
    protected TenantUserGrpSlaveVO getExistingUserGrp(Long id) {
        return null;
    }

    @Override
    protected List<TenantUserGrpSlaveVO> queryExistingUserGrps(TenantUserGrpDelParam delParam) {
        return null;
    }

    @Override
    protected int assignRolesToUserGrp(List<Long> roleIds, Long userGrpId, String operator) {
        return 0;
    }

    @Override
    protected int removeRolesFromUserGrp(List<Long> roleIds, Long userGrpId, String operator) {
        return 0;
    }

    @Override
    protected int removeRolesFromUserGrps(List<Long> userGrpIds) {
        return 0;
    }

    @Override
    protected int assignSysResRolesToUserGrp(List<Long> sysResRoleIds, Long userGrpId, String operator) {
        return 0;
    }

    @Override
    protected int removeSysResRolesFromUserGrp(List<Long> sysResRoleIds, Long userGrpId, String operator) {
        return 0;
    }

    @Override
    protected int removeSysResRolesFromUserGrps(List<Long> userGrpIds) {
        return 0;
    }

    @Override
    protected TenantUserGrpUpdateCtx getUpdateContext(TenantUserGrpMasterVO userGrp, TenantUserGrpSlaveVO existingUserGrp) {
        return null;
    }

    @Override
    protected void doSave(TenantUserGrp newEntity) {

    }

    @Override
    protected int doUpdate(TenantUserGrp example) {
        return 0;
    }

    @Override
    protected int doRemove(Map<String, Object> deleteParams) {
        return 0;
    }

    @Override
    public TenantUserGrp toNewEntity(TenantUserGrpMasterVO tenantUserGrpMasterVO) {
        return null;
    }

    @Override
    public TenantUserGrp toUpdatableObj(TenantUserGrpUpdatableObj tenantUserGrpUpdatableObj) {
        return null;
    }
}
