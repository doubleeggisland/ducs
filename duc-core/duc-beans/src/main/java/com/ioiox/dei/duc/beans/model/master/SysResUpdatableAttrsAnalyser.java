package com.ioiox.dei.duc.beans.model.master;

import com.ioiox.dei.core.vo.UpdatableAttr;
import com.ioiox.dei.core.vo.UpdatableObjAnalyser;
import com.ioiox.dei.core.vo.UpdatableVO;
import com.ioiox.dei.duc.beans.entity.SysRes;
import com.ioiox.dei.duc.beans.vo.std.master.SysResMasterStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.SysResSlaveStdVO;

public class SysResUpdatableAttrsAnalyser
        extends UpdatableObjAnalyser<SysResMasterStdVO, SysResSlaveStdVO, SysResUpdatableObj, SysResUpdateCtx> {

    @Override
    public SysResUpdateCtx analyseUpdatedAttrs(final SysResMasterStdVO sysRes, final SysResSlaveStdVO existingSysRes) {
        final SysResUpdateCtx updateCtx = new SysResUpdateCtx();
        updateCtx.setUpdatableObj(new SysResUpdatableObj());
        analyseUpdatedAttrs(sysRes, existingSysRes, updateCtx);
        return updateCtx;
    }

    @Override
    protected void analyseUpdatedAttrs(final SysResMasterStdVO sysRes, final SysResSlaveStdVO existingSysRes, final SysResUpdateCtx updateCtx) {
        if (UpdatableVO.modified(existingSysRes.getCode(), sysRes.getCode())) {
            updateCtx.getUpdatableObj().setCode(new UpdatableAttr<>(SysRes.ShowColumn.CODE.getCode(), existingSysRes.getCode(), sysRes.getCode()));
        }
        if (UpdatableVO.modified(existingSysRes.getName(), sysRes.getName())) {
            updateCtx.getUpdatableObj().setName(new UpdatableAttr<>(SysRes.ShowColumn.NAME.getCode(), existingSysRes.getName(), sysRes.getName()));
        }
        if (UpdatableVO.modified(existingSysRes.getType(), sysRes.getType())) {
            updateCtx.getUpdatableObj().setType(new UpdatableAttr<>(SysRes.ShowColumn.TYPE.getCode(), existingSysRes.getType(), sysRes.getType()));
        }
        if (UpdatableVO.modified(existingSysRes.getStatus(), sysRes.getStatus())) {
            updateCtx.getUpdatableObj().setStatus(new UpdatableAttr<>(SysRes.ShowColumn.STATUS.getCode(), existingSysRes.getStatus(), sysRes.getStatus()));
        }
        if (UpdatableVO.modified(existingSysRes.getMemo(), sysRes.getMemo())) {
            updateCtx.getUpdatableObj().setMemo(new UpdatableAttr<>(SysRes.ShowColumn.MEMO.getCode(), existingSysRes.getMemo(), sysRes.getMemo()));
        }
        if (UpdatableVO.modified(existingSysRes.getSysPrjModuleName(), sysRes.getSysPrjModuleName())) {
            updateCtx.getUpdatableObj().setSysPrjModuleName(new UpdatableAttr<>(SysRes.ShowColumn.SYS_PRJ_MODULE_NAME.getCode(), existingSysRes.getSysPrjModuleName(), sysRes.getSysPrjModuleName()));
        }
        if (UpdatableVO.modified(existingSysRes.getSysPrjModuleCode(), sysRes.getSysPrjModuleCode())) {
            updateCtx.getUpdatableObj().setSysPrjModuleCode(new UpdatableAttr<>(SysRes.ShowColumn.SYS_PRJ_MODULE_CODE.getCode(), existingSysRes.getSysPrjModuleCode(), sysRes.getSysPrjModuleCode()));
        }
    }
}
