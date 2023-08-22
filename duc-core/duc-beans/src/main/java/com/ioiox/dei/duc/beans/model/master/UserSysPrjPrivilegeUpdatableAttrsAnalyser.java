package com.ioiox.dei.duc.beans.model.master;

import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableAttr;
import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableObj;
import com.ioiox.dei.core.orm.mybatis.model.std.data.UpdatableObjAnalyser;
import com.ioiox.dei.duc.beans.entity.UserSysPrjPrivilege;
import com.ioiox.dei.duc.beans.vo.std.master.UserSysPrjPrivilegeMasterStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.UserSysPrjPrivilegeSlaveStdVO;

public class UserSysPrjPrivilegeUpdatableAttrsAnalyser
        extends UpdatableObjAnalyser<UserSysPrjPrivilegeMasterStdVO, UserSysPrjPrivilegeSlaveStdVO, UserSysPrjPrivilegeUpdatableObj, UserSysPrjPrivilegeUpdateCtx> {

    @Override
    public UserSysPrjPrivilegeUpdateCtx analyseUpdatedAttrs(final UserSysPrjPrivilegeMasterStdVO sysPrjPrivilege,
                                                            final UserSysPrjPrivilegeSlaveStdVO existingSysPrjPrivilege) {
        final UserSysPrjPrivilegeUpdateCtx updateCtx = new UserSysPrjPrivilegeUpdateCtx();
        updateCtx.setUpdatableObj(new UserSysPrjPrivilegeUpdatableObj());
        analyseUpdatedAttrs(sysPrjPrivilege, existingSysPrjPrivilege, updateCtx);
        return updateCtx;
    }

    @Override
    protected void analyseUpdatedAttrs(final UserSysPrjPrivilegeMasterStdVO sysPrjPrivilege,
                                       final UserSysPrjPrivilegeSlaveStdVO existingSysPrjPrivilege,
                                       final UserSysPrjPrivilegeUpdateCtx updateCtx) {
        if (UpdatableObj.modified(existingSysPrjPrivilege.getAccessCondition(), sysPrjPrivilege.getAccessCondition())) {
            updateCtx.getUpdatableObj().addUpdatedAttrName(UserSysPrjPrivilege.ShowColumn.ACCESS_CONDITION.getCode());
            updateCtx.getUpdatableObj().setAccessCondition(new UpdatableAttr<>(UserSysPrjPrivilege.ShowColumn.ACCESS_CONDITION.getCode(), existingSysPrjPrivilege.getAccessCondition(), sysPrjPrivilege.getAccessCondition()));
        }
    }
}
