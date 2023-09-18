package com.ioiox.dei.duc.beans.model.master;

import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableAttr;
import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableObj;
import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableObjAnalyser;
import com.ioiox.dei.duc.beans.entity.Role;
import com.ioiox.dei.duc.beans.vo.std.master.BaseRoleMasterVO;
import com.ioiox.dei.duc.beans.vo.std.slave.BaseRoleSlaveVO;

public abstract class BaseRoleUpdatableAttrsAnalyser<
        M extends BaseRoleMasterVO,
        S extends BaseRoleSlaveVO,
        O extends BaseRoleUpdatableObj,
        C extends BaseRoleUpdateCtx<O>>
        extends UpdatableObjAnalyser<M, S, O, C> {

    @Override
    protected void analyseUpdatedAttrs(final M role, final S existingRole, final C updateCtx) {
        if (UpdatableObj.modified(existingRole.getName(), role.getName())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(Role.ShowColumn.NAME.getCode());
            updateCtx.getUpdatableObj().setName(new UpdatableAttr<>(Role.ShowColumn.NAME.getCode(), existingRole.getName(), role.getName()));
        }
        if (UpdatableObj.modified(existingRole.getType(), role.getType())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(Role.ShowColumn.TYPE.getCode());
            updateCtx.getUpdatableObj().setType(new UpdatableAttr<>(Role.ShowColumn.TYPE.getCode(), existingRole.getType(), role.getType()));
        }
        if (UpdatableObj.modified(existingRole.getStatus(), role.getStatus())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(Role.ShowColumn.STATUS.getCode());
            updateCtx.getUpdatableObj().setStatus(new UpdatableAttr<>(Role.ShowColumn.STATUS.getCode(), existingRole.getStatus(), role.getStatus()));
        }
        if (UpdatableObj.modified(existingRole.getMemo(), role.getMemo())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(Role.ShowColumn.MEMO.getCode());
            updateCtx.getUpdatableObj().setMemo(new UpdatableAttr<>(Role.ShowColumn.MEMO.getCode(), existingRole.getMemo(), role.getMemo()));
        }
    }
}
