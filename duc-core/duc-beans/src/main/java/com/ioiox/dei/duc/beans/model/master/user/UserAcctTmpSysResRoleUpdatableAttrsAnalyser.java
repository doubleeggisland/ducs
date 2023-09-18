package com.ioiox.dei.duc.beans.model.master.user;

import com.ioiox.dei.duc.beans.model.master.BaseTmpRoleUpdatableAttrsAnalyser;
import com.ioiox.dei.duc.beans.vo.std.master.user.UserAcctTmpSysResRoleMasterVO;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctTmpSysResRoleSlaveVO;

public class UserAcctTmpSysResRoleUpdatableAttrsAnalyser
        extends BaseTmpRoleUpdatableAttrsAnalyser<UserAcctTmpSysResRoleMasterVO, UserAcctTmpSysResRoleSlaveVO, UserAcctTmpSysResRoleUpdatableObj, UserAcctTmpSysResRoleUpdateCtx> {

    @Override
    public UserAcctTmpSysResRoleUpdateCtx analyseUpdatedAttrs(final UserAcctTmpSysResRoleMasterVO tmpSysResRole,
                                                              final UserAcctTmpSysResRoleSlaveVO existingTmpSysResRole) {
        final UserAcctTmpSysResRoleUpdateCtx updateCtx = new UserAcctTmpSysResRoleUpdateCtx();
        updateCtx.setUpdatableObj(new UserAcctTmpSysResRoleUpdatableObj());
        analyseUpdatedAttrs(tmpSysResRole, existingTmpSysResRole, updateCtx);
        return updateCtx;
    }
}
