package com.ioiox.dei.duc.beans.model.master;

import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableAttr;
import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableObj;
import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableObjAnalyser;
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
        if (UpdatableObj.modified(existingUserGrp.getCode(), userGrp.getCode())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(UserGrp.ShowColumn.CODE.getCode());
            updateCtx.getUpdatableObj().setCode(new UpdatableAttr<>(UserGrp.ShowColumn.CODE.getCode(), existingUserGrp.getCode(), userGrp.getCode()));
        }
        if (UpdatableObj.modified(existingUserGrp.getName(), userGrp.getName())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(UserGrp.ShowColumn.NAME.getCode());
            updateCtx.getUpdatableObj().setName(new UpdatableAttr<>(UserGrp.ShowColumn.NAME.getCode(), existingUserGrp.getName(), userGrp.getName()));
        }
        if (UpdatableObj.modified(existingUserGrp.getMemo(), userGrp.getMemo())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(UserGrp.ShowColumn.MEMO.getCode());
            updateCtx.getUpdatableObj().setMemo(new UpdatableAttr<>(UserGrp.ShowColumn.MEMO.getCode(), existingUserGrp.getMemo(), userGrp.getMemo()));
        }
        if (UpdatableObj.modified(existingUserGrp.getStatus(), userGrp.getStatus())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(UserGrp.ShowColumn.STATUS.getCode());
            updateCtx.getUpdatableObj().setStatus(new UpdatableAttr<>(UserGrp.ShowColumn.STATUS.getCode(), existingUserGrp.getStatus(), userGrp.getStatus()));
        }
    }
}
