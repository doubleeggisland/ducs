package com.ioiox.dei.duc.beans.model.master.tenant;

import com.ioiox.dei.duc.beans.model.master.SimpleRoleUpdatableAttrsAnalyser;
import com.ioiox.dei.duc.beans.vo.std.master.tenant.TenantUserRoleMasterVO;
import com.ioiox.dei.duc.beans.vo.std.slave.tenant.TenantUserRoleSlaveVO;

public class TenantUserRoleUpdatableAttrsAnalyser
        extends SimpleRoleUpdatableAttrsAnalyser<TenantUserRoleMasterVO, TenantUserRoleSlaveVO, TenantUserRoleUpdatableObj, TenantUserRoleUpdateCtx> {

    @Override
    public TenantUserRoleUpdateCtx analyseUpdatedAttrs(final TenantUserRoleMasterVO role, final TenantUserRoleSlaveVO existingRole) {
        final TenantUserRoleUpdateCtx updateCtx = new TenantUserRoleUpdateCtx();
        updateCtx.setUpdatableObj(new TenantUserRoleUpdatableObj());
        analyseUpdatedAttrs(role, existingRole, updateCtx);
        return updateCtx;
    }
}
