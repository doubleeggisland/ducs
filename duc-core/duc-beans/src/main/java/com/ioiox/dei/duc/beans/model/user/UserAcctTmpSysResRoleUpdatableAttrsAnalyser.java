package com.ioiox.dei.duc.beans.model.user;

import com.ioiox.dei.duc.beans.model.BaseTmpRoleUpdatableAttrsAnalyser;
import com.ioiox.dei.duc.beans.vo.std.master.user.UserAcctTmpSysResRoleMasterStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctTmpSysResRoleSlaveStdVO;

public class UserAcctTmpSysResRoleUpdatableAttrsAnalyser
        extends BaseTmpRoleUpdatableAttrsAnalyser<UserAcctTmpSysResRoleMasterStdVO, UserAcctTmpSysResRoleSlaveStdVO, UserAcctTmpSysResRoleUpdatableObj, UserAcctTmpSysResRoleUpdateCtx> {

    @Override
    public UserAcctTmpSysResRoleUpdateCtx analyseUpdatedAttrs(final UserAcctTmpSysResRoleMasterStdVO tmpSysResRole,
                                                              final UserAcctTmpSysResRoleSlaveStdVO existingTmpSysResRole) {
        final UserAcctTmpSysResRoleUpdateCtx updateCtx = new UserAcctTmpSysResRoleUpdateCtx();
        updateCtx.setUpdatableObj(new UserAcctTmpSysResRoleUpdatableObj());
        analyseUpdatedAttrs(tmpSysResRole, existingTmpSysResRole, updateCtx);
        return updateCtx;
    }
}
