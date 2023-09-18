package com.ioiox.dei.duc.beans.model.master;

import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableAttr;
import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableObj;
import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableObjAnalyser;
import com.ioiox.dei.duc.beans.entity.BaseUser;
import com.ioiox.dei.duc.beans.vo.std.master.UserMasterVO;
import com.ioiox.dei.duc.beans.vo.std.slave.BaseUserSlaveVO;

public abstract class UserUpdatableAttrsAnalyser<
        M extends UserMasterVO,
        S extends BaseUserSlaveVO,
        O extends UserUpdatableObj,
        C extends UserUpdateCtx<O>>
        extends UpdatableObjAnalyser<M, S, O, C> {

    @Override
    protected void analyseUpdatedAttrs(final M acct, final S existingAcct, final C updateCtx) {
        if (UpdatableObj.modified(existingAcct.getUsername(), acct.getUsername())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(BaseUser.ShowColumn.USER_NAME.getCode());
            updateCtx.getUpdatableObj().setUsername(new UpdatableAttr<>(BaseUser.ShowColumn.USER_NAME.getCode(), existingAcct.getUsername(), acct.getUsername()));
        }
        if (UpdatableObj.modified(existingAcct.getNickName(), acct.getNickName())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(BaseUser.ShowColumn.NICK_NAME.getCode());
            updateCtx.getUpdatableObj().setNickName(new UpdatableAttr<>(BaseUser.ShowColumn.NICK_NAME.getCode(), existingAcct.getNickName(), acct.getNickName()));
        }
        if (UpdatableObj.modified(existingAcct.getMobile(), acct.getMobile())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(BaseUser.ShowColumn.MOBILE.getCode());
            updateCtx.getUpdatableObj().setMobile(new UpdatableAttr<>(BaseUser.ShowColumn.MOBILE.getCode(), existingAcct.getMobile(), acct.getMobile(), true));
        }
        if (UpdatableObj.modified(existingAcct.getEmail(), acct.getEmail())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(BaseUser.ShowColumn.EMAIL.getCode());
            updateCtx.getUpdatableObj().setEmail(new UpdatableAttr<>(BaseUser.ShowColumn.EMAIL.getCode(), existingAcct.getEmail(), acct.getEmail(), true));
        }
        if (UpdatableObj.modified(existingAcct.getStatus(), acct.getStatus())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(BaseUser.ShowColumn.STATUS.getCode());
            updateCtx.getUpdatableObj().setStatus(new UpdatableAttr<>(BaseUser.ShowColumn.STATUS.getCode(), existingAcct.getStatus(), acct.getStatus()));
        }
        if (UpdatableObj.modified(existingAcct.getPwd(), acct.getPwd())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(BaseUser.ShowColumn.PWD.getCode());
            updateCtx.getUpdatableObj().setPwd(new UpdatableAttr<>(BaseUser.ShowColumn.PWD.getCode(), existingAcct.getPwd(), acct.getPwd(), true));
        }
        if (UpdatableObj.modified(existingAcct.getAvatarUrl(), acct.getAvatarUrl())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(BaseUser.ShowColumn.AVATAR_URL.getCode());
            updateCtx.getUpdatableObj().setAvatarUrl(new UpdatableAttr<>(BaseUser.ShowColumn.AVATAR_URL.getCode(), existingAcct.getAvatarUrl(), acct.getAvatarUrl()));
        }
    }
}
