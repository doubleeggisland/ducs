package com.ioiox.dei.duc.beans.model.master.tenant;

import com.ioiox.dei.duc.beans.model.master.UserGrpUpdatableAttrsAnalyser;
import com.ioiox.dei.duc.beans.vo.std.master.tenant.TenantUserGrpMasterVO;
import com.ioiox.dei.duc.beans.vo.std.slave.tenant.TenantUserGrpSlaveVO;

public class TenantUserGrpUpdatableAttrsAnalyser
        extends UserGrpUpdatableAttrsAnalyser<TenantUserGrpMasterVO, TenantUserGrpSlaveVO, TenantUserGrpUpdatableObj, TenantUserGrpUpdateCtx> {

    @Override
    public TenantUserGrpUpdateCtx analyseUpdatedAttrs(final TenantUserGrpMasterVO userGrp,
                                                      final TenantUserGrpSlaveVO existingUserGrp) {
        final TenantUserGrpUpdateCtx updateCtx = new TenantUserGrpUpdateCtx();
        updateCtx.setUpdatableObj(new TenantUserGrpUpdatableObj());
        analyseUpdatedAttrs(userGrp, existingUserGrp, updateCtx);
        return updateCtx;
    }
}
