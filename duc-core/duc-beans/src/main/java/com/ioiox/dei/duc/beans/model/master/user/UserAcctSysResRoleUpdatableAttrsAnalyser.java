package com.ioiox.dei.duc.beans.model.master.user;

import com.ioiox.dei.duc.beans.model.master.SimpleRoleUpdatableAttrsAnalyser;
import com.ioiox.dei.duc.beans.vo.std.master.user.UserAcctSysResRoleMasterVO;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctSysResRoleSlaveVO;

public class UserAcctSysResRoleUpdatableAttrsAnalyser
        extends SimpleRoleUpdatableAttrsAnalyser<UserAcctSysResRoleMasterVO, UserAcctSysResRoleSlaveVO, UserAcctSysResRoleUpdatableObj, UserAcctSysResRoleUpdateCtx> {

    @Override
    public UserAcctSysResRoleUpdateCtx analyseUpdatedAttrs(final UserAcctSysResRoleMasterVO sysResRole,
                                                           final UserAcctSysResRoleSlaveVO existingSysResRole) {
        final UserAcctSysResRoleUpdateCtx updateCtx = new UserAcctSysResRoleUpdateCtx();
        updateCtx.setUpdatableObj(new UserAcctSysResRoleUpdatableObj());
        analyseUpdatedAttrs(sysResRole, existingSysResRole, updateCtx);
        return updateCtx;
    }
}
