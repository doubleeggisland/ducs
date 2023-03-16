package com.ioiox.dei.duc.beans.model;

import com.ioiox.dei.core.vo.UpdatableAttr;
import com.ioiox.dei.core.vo.UpdatableObjAnalyser;
import com.ioiox.dei.core.vo.UpdatableVO;
import com.ioiox.dei.duc.beans.entity.Role;
import com.ioiox.dei.duc.beans.vo.std.master.BaseRoleMasterStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.BaseRoleSlaveStdVO;

public abstract class RoleUpdatableAttrsAnalyser<
        M extends BaseRoleMasterStdVO,
        S extends BaseRoleSlaveStdVO,
        O extends RoleUpdatableObj,
        C extends RoleUpdateCtx<O>>
        extends UpdatableObjAnalyser<M, S, O, C> {

    @Override
    protected void analyseUpdatedAttrs(final M role, final S existingRole, final C updateCtx) {
        if (UpdatableVO.modified(existingRole.getName(), role.getName())) {
            updateCtx.getUpdatableObj().setName(new UpdatableAttr<>(Role.ShowColumn.NAME.getCode(), existingRole.getName(), role.getName()));
        }
        if (UpdatableVO.modified(existingRole.getType(), role.getType())) {
            updateCtx.getUpdatableObj().setType(new UpdatableAttr<>(Role.ShowColumn.TYPE.getCode(), existingRole.getType(), role.getType()));
        }
        if (UpdatableVO.modified(existingRole.getStatus(), role.getStatus())) {
            updateCtx.getUpdatableObj().setStatus(new UpdatableAttr<>(Role.ShowColumn.STATUS.getCode(), existingRole.getStatus(), role.getStatus()));
        }
        if (UpdatableVO.modified(existingRole.getMemo(), role.getMemo())) {
            updateCtx.getUpdatableObj().setMemo(new UpdatableAttr<>(Role.ShowColumn.MEMO.getCode(), existingRole.getMemo(), role.getMemo()));
        }
    }
}
