package com.ioiox.dei.duc.beans.model.master.user;

import com.ioiox.dei.duc.beans.model.master.BaseRoleUpdatableAttrsAnalyser;
import com.ioiox.dei.duc.beans.vo.std.master.user.UserAcctSysResRoleMasterStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctSysResRoleSlaveStdVO;

public class UserAcctSysResRoleUpdatableAttrsAnalyser
        extends BaseRoleUpdatableAttrsAnalyser<UserAcctSysResRoleMasterStdVO, UserAcctSysResRoleSlaveStdVO, UserAcctSysResRoleUpdatableObj, UserAcctSysResRoleUpdateCtx> {

    @Override
    public UserAcctSysResRoleUpdateCtx analyseUpdatedAttrs(final UserAcctSysResRoleMasterStdVO sysResRole,
                                                           final UserAcctSysResRoleSlaveStdVO existingSysResRole) {
        final UserAcctSysResRoleUpdateCtx updateCtx = new UserAcctSysResRoleUpdateCtx();
        updateCtx.setUpdatableObj(new UserAcctSysResRoleUpdatableObj());
        analyseUpdatedAttrs(sysResRole, existingSysResRole, updateCtx);
        return updateCtx;
    }
}
