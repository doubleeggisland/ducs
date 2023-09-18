package com.ioiox.dei.duc.beans.model.master.tenant;

import com.ioiox.dei.duc.beans.model.master.UserUpdatableAttrsAnalyser;
import com.ioiox.dei.duc.beans.vo.std.master.tenant.TenantUserMasterVO;
import com.ioiox.dei.duc.beans.vo.std.slave.tenant.TenantUserSlaveVO;

public class TenantUserUpdatableAttrsAnalyser
        extends UserUpdatableAttrsAnalyser<TenantUserMasterVO, TenantUserSlaveVO, TenantUserUpdatableObj, TenantUserUpdateCtx> {

    @Override
    public TenantUserUpdateCtx analyseUpdatedAttrs(final TenantUserMasterVO tenantUser,
                                                   final TenantUserSlaveVO existingTenantUser) {
        final TenantUserUpdateCtx updateCtx = new TenantUserUpdateCtx();
        updateCtx.setUpdatableObj(new TenantUserUpdatableObj());
        analyseUpdatedAttrs(tenantUser, existingTenantUser, updateCtx);
        return updateCtx;
    }
}
