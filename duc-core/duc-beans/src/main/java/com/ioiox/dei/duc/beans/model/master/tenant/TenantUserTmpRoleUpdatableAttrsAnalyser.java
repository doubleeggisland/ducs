package com.ioiox.dei.duc.beans.model.master.tenant;

import com.ioiox.dei.duc.beans.model.master.SimpleRoleUpdatableAttrsAnalyser;
import com.ioiox.dei.duc.beans.vo.std.master.tenant.TenantUserTmpRoleMasterVO;
import com.ioiox.dei.duc.beans.vo.std.slave.tenant.TenantUserTmpRoleSlaveVO;

public class TenantUserTmpRoleUpdatableAttrsAnalyser
        extends SimpleRoleUpdatableAttrsAnalyser<TenantUserTmpRoleMasterVO, TenantUserTmpRoleSlaveVO, TenantUserTmpRoleUpdatableObj, TenantUserTmpRoleUpdateCtx> {

    @Override
    public TenantUserTmpRoleUpdateCtx analyseUpdatedAttrs(final TenantUserTmpRoleMasterVO tmpRole,
                                                          final TenantUserTmpRoleSlaveVO existingTmpRole) {
        final TenantUserTmpRoleUpdateCtx updateCtx = new TenantUserTmpRoleUpdateCtx();
        updateCtx.setUpdatableObj(new TenantUserTmpRoleUpdatableObj());
        analyseUpdatedAttrs(tmpRole, existingTmpRole, updateCtx);
        return updateCtx;
    }
}
