package com.ioiox.dei.duc.beans.model.master;

import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableAttr;
import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableObj;
import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableObjAnalyser;
import com.ioiox.dei.duc.beans.entity.UserSysPrjPrivilege;
import com.ioiox.dei.duc.beans.vo.std.master.UserSysPrjPrivilegeMasterVO;
import com.ioiox.dei.duc.beans.vo.std.slave.UserSysPrjPrivilegeSlaveVO;

public class UserSysPrjPrivilegeUpdatableAttrsAnalyser
        extends UpdatableObjAnalyser<UserSysPrjPrivilegeMasterVO, UserSysPrjPrivilegeSlaveVO, UserSysPrjPrivilegeUpdatableObj, UserSysPrjPrivilegeUpdateCtx> {

    @Override
    public UserSysPrjPrivilegeUpdateCtx analyseUpdatedAttrs(final UserSysPrjPrivilegeMasterVO sysPrjPrivilege,
                                                            final UserSysPrjPrivilegeSlaveVO existingSysPrjPrivilege) {
        final UserSysPrjPrivilegeUpdateCtx updateCtx = new UserSysPrjPrivilegeUpdateCtx();
        updateCtx.setUpdatableObj(new UserSysPrjPrivilegeUpdatableObj());
        analyseUpdatedAttrs(sysPrjPrivilege, existingSysPrjPrivilege, updateCtx);
        return updateCtx;
    }

    @Override
    protected void analyseUpdatedAttrs(final UserSysPrjPrivilegeMasterVO sysPrjPrivilege,
                                       final UserSysPrjPrivilegeSlaveVO existingSysPrjPrivilege,
                                       final UserSysPrjPrivilegeUpdateCtx updateCtx) {
        if (UpdatableObj.modified(existingSysPrjPrivilege.getAccessCondition(), sysPrjPrivilege.getAccessCondition())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(UserSysPrjPrivilege.ShowColumn.ACCESS_CONDITION.getCode());
            updateCtx.getUpdatableObj().setAccessCondition(new UpdatableAttr<>(UserSysPrjPrivilege.ShowColumn.ACCESS_CONDITION.getCode(), existingSysPrjPrivilege.getAccessCondition(), sysPrjPrivilege.getAccessCondition()));
        }
    }
}
