package com.ioiox.dei.duc.beans.model.master.tenant;

import com.ioiox.dei.duc.beans.model.master.SimpleRoleUpdatableAttrsAnalyser;
import com.ioiox.dei.duc.beans.vo.std.master.tenant.TenantUserSysResRoleMasterVO;
import com.ioiox.dei.duc.beans.vo.std.slave.tenant.TenantUserSysResRoleSlaveVO;

public class TenantUserSysResRoleUpdatableAttrsAnalyser
        extends SimpleRoleUpdatableAttrsAnalyser<TenantUserSysResRoleMasterVO, TenantUserSysResRoleSlaveVO, TenantUserSysResRoleUpdatableObj, TenantUserSysResRoleUpdateCtx> {

    @Override
    public TenantUserSysResRoleUpdateCtx analyseUpdatedAttrs(final TenantUserSysResRoleMasterVO sysResRole,
                                                             final TenantUserSysResRoleSlaveVO existingSysResRole) {
        final TenantUserSysResRoleUpdateCtx updateCtx = new TenantUserSysResRoleUpdateCtx();
        updateCtx.setUpdatableObj(new TenantUserSysResRoleUpdatableObj());
        analyseUpdatedAttrs(sysResRole, existingSysResRole, updateCtx);
        return updateCtx;
    }
}
