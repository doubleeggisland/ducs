package com.ioiox.dei.duc.beans.model.master.tenant;

import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableAttr;
import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableObj;
import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableObjAnalyser;
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
        if (UpdatableObj.modified(existingTenant.getCode(), tenant.getCode())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(Tenant.ShowColumn.CODE.getCode());
            updateCtx.getUpdatableObj().setCode(new UpdatableAttr<>(Tenant.ShowColumn.CODE.getCode(), existingTenant.getCode(), tenant.getCode()));
        }
        if (UpdatableObj.modified(existingTenant.getName(), tenant.getName())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(Tenant.ShowColumn.NAME.getCode());
            updateCtx.getUpdatableObj().setName(new UpdatableAttr<>(Tenant.ShowColumn.NAME.getCode(), existingTenant.getName(), tenant.getName()));
        }
        if (UpdatableObj.modified(existingTenant.getMemo(), tenant.getMemo())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(Tenant.ShowColumn.MEMO.getCode());
            updateCtx.getUpdatableObj().setMemo(new UpdatableAttr<>(Tenant.ShowColumn.MEMO.getCode(), existingTenant.getMemo(), tenant.getMemo()));
        }
        if (UpdatableObj.modified(existingTenant.getStatus(), tenant.getStatus())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(Tenant.ShowColumn.STATUS.getCode());
            updateCtx.getUpdatableObj().setStatus(new UpdatableAttr<>(Tenant.ShowColumn.STATUS.getCode(), existingTenant.getStatus(), tenant.getStatus()));
        }
        if (UpdatableObj.modified(existingTenant.getLvl(), tenant.getLvl())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(Tenant.ShowColumn.LEVEL.getCode());
            updateCtx.getUpdatableObj().setLvl(new UpdatableAttr<>(Tenant.ShowColumn.LEVEL.getCode(), existingTenant.getLvl(), tenant.getLvl()));
        }
        if (UpdatableObj.modified(existingTenant.getPid(), tenant.getPid())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(Tenant.ShowColumn.PARENT_SID.getCode());
            updateCtx.getUpdatableObj().setPid(new UpdatableAttr<>(Tenant.ShowColumn.PARENT_SID.getCode(), existingTenant.getPid(), tenant.getPid()));
        }
        if (UpdatableObj.modified(existingTenant.getRid(), tenant.getRid())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(Tenant.ShowColumn.ROOT_SID.getCode());
            updateCtx.getUpdatableObj().setRid(new UpdatableAttr<>(Tenant.ShowColumn.ROOT_SID.getCode(), existingTenant.getRid(), tenant.getRid()));
        }
    }
}
