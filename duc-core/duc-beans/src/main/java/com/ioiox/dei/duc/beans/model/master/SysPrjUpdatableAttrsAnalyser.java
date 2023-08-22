package com.ioiox.dei.duc.beans.model.master;

import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableAttr;
import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableObj;
import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableObjAnalyser;
import com.ioiox.dei.duc.beans.entity.SysPrj;
import com.ioiox.dei.duc.beans.vo.std.master.SysPrjMasterStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.SysPrjSlaveStdVO;

public class SysPrjUpdatableAttrsAnalyser
        extends UpdatableObjAnalyser<SysPrjMasterStdVO, SysPrjSlaveStdVO, SysPrjUpdatableObj, SysPrjUpdateCtx> {

    @Override
    public SysPrjUpdateCtx analyseUpdatedAttrs(final SysPrjMasterStdVO sysPrj, final SysPrjSlaveStdVO existingSysPrj) {
        final SysPrjUpdateCtx updateCtx = new SysPrjUpdateCtx();
        updateCtx.setUpdatableObj(new SysPrjUpdatableObj());
        analyseUpdatedAttrs(sysPrj, existingSysPrj, updateCtx);
        return updateCtx;
    }

    @Override
    protected void analyseUpdatedAttrs(final SysPrjMasterStdVO sysPrj, final SysPrjSlaveStdVO existingSysPrj, final SysPrjUpdateCtx updateCtx) {
        if (UpdatableObj.modified(existingSysPrj.getCode(), sysPrj.getCode())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(SysPrj.ShowColumn.CODE.getCode());
            updateCtx.getUpdatableObj().setCode(new UpdatableAttr<>(SysPrj.ShowColumn.CODE.getCode(), existingSysPrj.getCode(), sysPrj.getCode()));
        }
        if (UpdatableObj.modified(existingSysPrj.getName(), sysPrj.getName())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(SysPrj.ShowColumn.NAME.getCode());
            updateCtx.getUpdatableObj().setName(new UpdatableAttr<>(SysPrj.ShowColumn.NAME.getCode(), existingSysPrj.getName(), sysPrj.getName()));
        }
        if (UpdatableObj.modified(existingSysPrj.getMemo(), sysPrj.getMemo())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(SysPrj.ShowColumn.MEMO.getCode());
            updateCtx.getUpdatableObj().setMemo(new UpdatableAttr<>(SysPrj.ShowColumn.MEMO.getCode(), existingSysPrj.getMemo(), sysPrj.getMemo()));
        }
        if (UpdatableObj.modified(existingSysPrj.getStatus(), sysPrj.getStatus())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(SysPrj.ShowColumn.STATUS.getCode());
            updateCtx.getUpdatableObj().setStatus(new UpdatableAttr<>(SysPrj.ShowColumn.STATUS.getCode(), existingSysPrj.getStatus(), sysPrj.getStatus()));
        }
    }
}
