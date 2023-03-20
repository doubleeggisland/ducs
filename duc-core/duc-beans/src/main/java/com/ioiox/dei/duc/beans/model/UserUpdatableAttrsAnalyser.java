package com.ioiox.dei.duc.beans.model;

import com.ioiox.dei.core.vo.UpdatableAttr;
import com.ioiox.dei.core.vo.UpdatableObjAnalyser;
import com.ioiox.dei.core.vo.UpdatableVO;
import com.ioiox.dei.duc.beans.entity.BaseUser;
import com.ioiox.dei.duc.beans.vo.std.master.UserMasterStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.BaseUserSlaveStdVO;

public abstract class UserUpdatableAttrsAnalyser<
        M extends UserMasterStdVO,
        S extends BaseUserSlaveStdVO,
        O extends UserUpdatableObj,
        C extends UserUpdateCtx<O>>
        extends UpdatableObjAnalyser<M, S, O, C> {

    @Override
    protected void analyseUpdatedAttrs(final M acct, final S existingAcct, final C updateCtx) {
        if (UpdatableVO.modified(existingAcct.getUsername(), acct.getUsername())) {
            updateCtx.getUpdatableObj().setUsername(new UpdatableAttr<>(BaseUser.ShowColumn.USER_NAME.getCode(), existingAcct.getUsername(), acct.getUsername()));
        }
        if (UpdatableVO.modified(existingAcct.getNickName(), acct.getNickName())) {
            updateCtx.getUpdatableObj().setNickName(new UpdatableAttr<>(BaseUser.ShowColumn.NICK_NAME.getCode(), existingAcct.getNickName(), acct.getNickName()));
        }
        if (UpdatableVO.modified(existingAcct.getMobile(), acct.getMobile())) {
            updateCtx.getUpdatableObj().setMobile(new UpdatableAttr<>(BaseUser.ShowColumn.MOBILE.getCode(), existingAcct.getMobile(), acct.getMobile(), true));
        }
        if (UpdatableVO.modified(existingAcct.getEmail(), acct.getEmail())) {
            updateCtx.getUpdatableObj().setEmail(new UpdatableAttr<>(BaseUser.ShowColumn.EMAIL.getCode(), existingAcct.getEmail(), acct.getEmail(), true));
        }
        if (UpdatableVO.modified(existingAcct.getStatus(), acct.getStatus())) {
            updateCtx.getUpdatableObj().setStatus(new UpdatableAttr<>(BaseUser.ShowColumn.STATUS.getCode(), existingAcct.getStatus(), acct.getStatus()));
        }
        if (UpdatableVO.modified(existingAcct.getPwd(), acct.getPwd())) {
            updateCtx.getUpdatableObj().setPwd(new UpdatableAttr<>(BaseUser.ShowColumn.PWD.getCode(), existingAcct.getPwd(), acct.getPwd(), true));
        }
        if (UpdatableVO.modified(existingAcct.getAvatarUrl(), acct.getAvatarUrl())) {
            updateCtx.getUpdatableObj().setAvatarUrl(new UpdatableAttr<>(BaseUser.ShowColumn.AVATAR_URL.getCode(), existingAcct.getAvatarUrl(), acct.getAvatarUrl()));
        }
    }
}
