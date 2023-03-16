package com.ioiox.dei.duc.beans.model;

import com.ioiox.dei.core.vo.UpdatableAttr;
import com.ioiox.dei.core.vo.UpdatableObjAnalyser;
import com.ioiox.dei.core.vo.UpdatableVO;
import com.ioiox.dei.duc.beans.entity.Acct;
import com.ioiox.dei.duc.beans.vo.std.master.AcctMasterStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.BaseAcctSlaveStdVO;

public abstract class AcctUpdatableAttrsAnalyser<
        M extends AcctMasterStdVO,
        S extends BaseAcctSlaveStdVO,
        O extends AcctUpdatableObj,
        C extends AcctUpdateCtx<O>>
        extends UpdatableObjAnalyser<M, S, O, C> {

    @Override
    protected void analyseUpdatedAttrs(final M acct, final S existingAcct, final C updateCtx) {
        if (UpdatableVO.modified(existingAcct.getUsername(), acct.getUsername())) {
            updateCtx.getUpdatableObj().setUsername(new UpdatableAttr<>(Acct.ShowColumn.USER_NAME.getCode(), existingAcct.getUsername(), acct.getUsername()));
        }
        if (UpdatableVO.modified(existingAcct.getNickName(), acct.getNickName())) {
            updateCtx.getUpdatableObj().setNickName(new UpdatableAttr<>(Acct.ShowColumn.NICK_NAME.getCode(), existingAcct.getNickName(), acct.getNickName()));
        }
        if (UpdatableVO.modified(existingAcct.getMobile(), acct.getMobile())) {
            updateCtx.getUpdatableObj().setMobile(new UpdatableAttr<>(Acct.ShowColumn.MOBILE.getCode(), existingAcct.getMobile(), acct.getMobile(), true));
        }
        if (UpdatableVO.modified(existingAcct.getEmail(), acct.getEmail())) {
            updateCtx.getUpdatableObj().setEmail(new UpdatableAttr<>(Acct.ShowColumn.EMAIL.getCode(), existingAcct.getEmail(), acct.getEmail(), true));
        }
        if (UpdatableVO.modified(existingAcct.getStatus(), acct.getStatus())) {
            updateCtx.getUpdatableObj().setStatus(new UpdatableAttr<>(Acct.ShowColumn.STATUS.getCode(), existingAcct.getStatus(), acct.getStatus()));
        }
        if (UpdatableVO.modified(existingAcct.getPwd(), acct.getPwd())) {
            updateCtx.getUpdatableObj().setPwd(new UpdatableAttr<>(Acct.ShowColumn.PWD.getCode(), existingAcct.getPwd(), acct.getPwd(), true));
        }
        if (UpdatableVO.modified(existingAcct.getAvatarUrl(), acct.getAvatarUrl())) {
            updateCtx.getUpdatableObj().setAvatarUrl(new UpdatableAttr<>(Acct.ShowColumn.AVATAR_URL.getCode(), existingAcct.getAvatarUrl(), acct.getAvatarUrl()));
        }
    }
}
