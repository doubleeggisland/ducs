package com.ioiox.dei.duc.beans.model.master;

import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableAttr;
import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableObj;
import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableObjAnalyser;
import com.ioiox.dei.duc.beans.entity.SysRes;
import com.ioiox.dei.duc.beans.vo.std.master.SysResMasterVO;
import com.ioiox.dei.duc.beans.vo.std.slave.SysResSlaveVO;

public class SysResUpdatableAttrsAnalyser
        extends UpdatableObjAnalyser<SysResMasterVO, SysResSlaveVO, SysResUpdatableObj, SysResUpdateCtx> {

    @Override
    public SysResUpdateCtx analyseUpdatedAttrs(final SysResMasterVO sysRes, final SysResSlaveVO existingSysRes) {
        final SysResUpdateCtx updateCtx = new SysResUpdateCtx();
        updateCtx.setUpdatableObj(new SysResUpdatableObj());
        analyseUpdatedAttrs(sysRes, existingSysRes, updateCtx);
        return updateCtx;
    }

    @Override
    protected void analyseUpdatedAttrs(final SysResMasterVO sysRes, final SysResSlaveVO existingSysRes, final SysResUpdateCtx updateCtx) {
        if (UpdatableObj.modified(existingSysRes.getCode(), sysRes.getCode())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(SysRes.ShowColumn.CODE.getCode());
            updateCtx.getUpdatableObj().setCode(new UpdatableAttr<>(SysRes.ShowColumn.CODE.getCode(), existingSysRes.getCode(), sysRes.getCode()));
        }
        if (UpdatableObj.modified(existingSysRes.getName(), sysRes.getName())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(SysRes.ShowColumn.NAME.getCode());
            updateCtx.getUpdatableObj().setName(new UpdatableAttr<>(SysRes.ShowColumn.NAME.getCode(), existingSysRes.getName(), sysRes.getName()));
        }
        if (UpdatableObj.modified(existingSysRes.getType(), sysRes.getType())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(SysRes.ShowColumn.TYPE.getCode());
            updateCtx.getUpdatableObj().setType(new UpdatableAttr<>(SysRes.ShowColumn.TYPE.getCode(), existingSysRes.getType(), sysRes.getType()));
        }
        if (UpdatableObj.modified(existingSysRes.getStatus(), sysRes.getStatus())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(SysRes.ShowColumn.STATUS.getCode());
            updateCtx.getUpdatableObj().setStatus(new UpdatableAttr<>(SysRes.ShowColumn.STATUS.getCode(), existingSysRes.getStatus(), sysRes.getStatus()));
        }
        if (UpdatableObj.modified(existingSysRes.getMemo(), sysRes.getMemo())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(SysRes.ShowColumn.MEMO.getCode());
            updateCtx.getUpdatableObj().setMemo(new UpdatableAttr<>(SysRes.ShowColumn.MEMO.getCode(), existingSysRes.getMemo(), sysRes.getMemo()));
        }
        if (UpdatableObj.modified(existingSysRes.getSysPrjModuleName(), sysRes.getSysPrjModuleName())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(SysRes.ShowColumn.SYS_PRJ_MODULE_NAME.getCode());
            updateCtx.getUpdatableObj().setSysPrjModuleName(new UpdatableAttr<>(SysRes.ShowColumn.SYS_PRJ_MODULE_NAME.getCode(), existingSysRes.getSysPrjModuleName(), sysRes.getSysPrjModuleName()));
        }
        if (UpdatableObj.modified(existingSysRes.getSysPrjModuleCode(), sysRes.getSysPrjModuleCode())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(SysRes.ShowColumn.SYS_PRJ_MODULE_CODE.getCode());
            updateCtx.getUpdatableObj().setSysPrjModuleCode(new UpdatableAttr<>(SysRes.ShowColumn.SYS_PRJ_MODULE_CODE.getCode(), existingSysRes.getSysPrjModuleCode(), sysRes.getSysPrjModuleCode()));
        }
    }
}
