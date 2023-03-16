package com.ioiox.dei.duc.beans.model.tenant;

import com.ioiox.dei.core.vo.UpdatableAttr;
import com.ioiox.dei.core.vo.UpdatableObjAnalyser;
import com.ioiox.dei.core.vo.UpdatableVO;
import com.ioiox.dei.duc.beans.entity.Tenant;
import com.ioiox.dei.duc.beans.vo.std.master.tenant.TenantMasterStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.tenant.TenantSlaveStdVO;

public class TenantUpdatableAttrsAnalyser
        extends UpdatableObjAnalyser<TenantMasterStdVO, TenantSlaveStdVO, TenantUpdatableObj, TenantUpdateCtx> {

    @Override
    public TenantUpdateCtx analyseUpdatedAttrs(final TenantMasterStdVO tenant,
                                               final TenantSlaveStdVO existingTenant) {
        return null;
    }

    @Override
    protected void analyseUpdatedAttrs(final TenantMasterStdVO tenant,
                                       final TenantSlaveStdVO existingTenant,
                                       final TenantUpdateCtx updateCtx) {
        if (UpdatableVO.modified(existingTenant.getCode(), tenant.getCode())) {
            updateCtx.getUpdatableObj().setCode(new UpdatableAttr<>(Tenant.ShowColumn.CODE.getCode(), existingTenant.getCode(), tenant.getCode()));
        }
        if (UpdatableVO.modified(existingTenant.getName(), tenant.getName())) {
            updateCtx.getUpdatableObj().setName(new UpdatableAttr<>(Tenant.ShowColumn.NAME.getCode(), existingTenant.getName(), tenant.getName()));
        }
        if (UpdatableVO.modified(existingTenant.getMemo(), tenant.getMemo())) {
            updateCtx.getUpdatableObj().setMemo(new UpdatableAttr<>(Tenant.ShowColumn.MEMO.getCode(), existingTenant.getMemo(), tenant.getMemo()));
        }
        if (UpdatableVO.modified(existingTenant.getStatus(), tenant.getStatus())) {
            updateCtx.getUpdatableObj().setStatus(new UpdatableAttr<>(Tenant.ShowColumn.STATUS.getCode(), existingTenant.getStatus(), tenant.getStatus()));
        }
        if (UpdatableVO.modified(existingTenant.getLvl(), tenant.getLvl())) {
            updateCtx.getUpdatableObj().setLvl(new UpdatableAttr<>(Tenant.ShowColumn.LEVEL.getCode(), existingTenant.getLvl(), tenant.getLvl()));
        }
        if (UpdatableVO.modified(existingTenant.getPid(), tenant.getPid())) {
            updateCtx.getUpdatableObj().setPid(new UpdatableAttr<>(Tenant.ShowColumn.PARENT_SID.getCode(), existingTenant.getPid(), tenant.getPid()));
        }
        if (UpdatableVO.modified(existingTenant.getRid(), tenant.getRid())) {
            updateCtx.getUpdatableObj().setRid(new UpdatableAttr<>(Tenant.ShowColumn.ROOT_SID.getCode(), existingTenant.getRid(), tenant.getRid()));
        }
    }
}
