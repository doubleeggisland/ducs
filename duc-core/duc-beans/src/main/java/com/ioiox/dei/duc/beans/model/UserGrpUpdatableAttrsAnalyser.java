package com.ioiox.dei.duc.beans.model;

import com.ioiox.dei.core.vo.UpdatableAttr;
import com.ioiox.dei.core.vo.UpdatableObjAnalyser;
import com.ioiox.dei.core.vo.UpdatableVO;
import com.ioiox.dei.duc.beans.entity.UserGrp;
import com.ioiox.dei.duc.beans.vo.std.master.BaseUserGrpMasterStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.BaseUserGrpSlaveStdVO;

public abstract class UserGrpUpdatableAttrsAnalyser<
        M extends BaseUserGrpMasterStdVO,
        S extends BaseUserGrpSlaveStdVO,
        O extends UserGrpUpdatableObj,
        C extends UserGrpUpdateCtx<O>>
        extends UpdatableObjAnalyser<M, S, O, C> {

    @Override
    protected void analyseUpdatedAttrs(final M userGrp, final S existingUserGrp, final C updateCtx) {
        if (UpdatableVO.modified(existingUserGrp.getCode(), userGrp.getCode())) {
            updateCtx.getUpdatableObj().setCode(new UpdatableAttr<>(UserGrp.ShowColumn.CODE.getCode(), existingUserGrp.getCode(), userGrp.getCode()));
        }
        if (UpdatableVO.modified(existingUserGrp.getName(), userGrp.getName())) {
            updateCtx.getUpdatableObj().setName(new UpdatableAttr<>(UserGrp.ShowColumn.NAME.getCode(), existingUserGrp.getName(), userGrp.getName()));
        }
        if (UpdatableVO.modified(existingUserGrp.getMemo(), userGrp.getMemo())) {
            updateCtx.getUpdatableObj().setMemo(new UpdatableAttr<>(UserGrp.ShowColumn.MEMO.getCode(), existingUserGrp.getMemo(), userGrp.getMemo()));
        }
        if (UpdatableVO.modified(existingUserGrp.getStatus(), userGrp.getStatus())) {
            updateCtx.getUpdatableObj().setStatus(new UpdatableAttr<>(UserGrp.ShowColumn.STATUS.getCode(), existingUserGrp.getStatus(), userGrp.getStatus()));
        }
    }
}