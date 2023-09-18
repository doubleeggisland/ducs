package com.ioiox.dei.duc.beans.model.master.tenant;

import com.ioiox.dei.duc.beans.model.master.BaseTmpRoleUpdatableAttrsAnalyser;
import com.ioiox.dei.duc.beans.vo.std.master.tenant.TenantUserTmpSysResRoleMasterVO;
import com.ioiox.dei.duc.beans.vo.std.slave.tenant.TenantUserTmpSysResRoleSlaveVO;

public class TenantUserTmpSysResRoleUpdatableAttrsAnalyser
        extends BaseTmpRoleUpdatableAttrsAnalyser<TenantUserTmpSysResRoleMasterVO, TenantUserTmpSysResRoleSlaveVO, TenantUserTmpSysResRoleUpdatableObj, TenantUserTmpSysResRoleUpdateCtx> {

    @Override
    public TenantUserTmpSysResRoleUpdateCtx analyseUpdatedAttrs(final TenantUserTmpSysResRoleMasterVO tmpSysResRole,
                                                                final TenantUserTmpSysResRoleSlaveVO existingTmpSysResRole) {
        final TenantUserTmpSysResRoleUpdateCtx updateCtx = new TenantUserTmpSysResRoleUpdateCtx();
        updateCtx.setUpdatableObj(new TenantUserTmpSysResRoleUpdatableObj());
        analyseUpdatedAttrs(tmpSysResRole, existingTmpSysResRole, updateCtx);
        return updateCtx;
    }
}
