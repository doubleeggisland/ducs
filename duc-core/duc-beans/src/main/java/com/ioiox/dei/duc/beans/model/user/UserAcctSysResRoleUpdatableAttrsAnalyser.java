package com.ioiox.dei.duc.beans.model.user;

import com.ioiox.dei.duc.beans.model.SysResRoleUpdatableAttrsAnalyser;
import com.ioiox.dei.duc.beans.vo.std.master.user.UserAcctSysResRoleMasterStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctSysResRoleSlaveStdVO;

public class UserAcctSysResRoleUpdatableAttrsAnalyser
        extends SysResRoleUpdatableAttrsAnalyser<UserAcctSysResRoleMasterStdVO, UserAcctSysResRoleSlaveStdVO, UserAcctSysResRoleUpdatableObj, UserAcctSysResRoleUpdateCtx> {

    @Override
    public UserAcctSysResRoleUpdateCtx analyseUpdatedAttrs(final UserAcctSysResRoleMasterStdVO sysResRole,
                                                           final UserAcctSysResRoleSlaveStdVO existingSysResRole) {
        final UserAcctSysResRoleUpdateCtx updateCtx = new UserAcctSysResRoleUpdateCtx();
        updateCtx.setUpdatableObj(new UserAcctSysResRoleUpdatableObj());
        analyseUpdatedAttrs(sysResRole, existingSysResRole, updateCtx);
        return updateCtx;
    }
}
