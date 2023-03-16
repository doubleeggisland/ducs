package com.ioiox.dei.duc.beans.model.user;

import com.ioiox.dei.duc.beans.model.TmpRoleUpdatableAttrsAnalyser;
import com.ioiox.dei.duc.beans.vo.std.master.user.UserAcctTmpRoleMasterStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctTmpRoleSlaveStdVO;

public class UserAcctTmpRoleUpdatableAttrsAnalyser
        extends TmpRoleUpdatableAttrsAnalyser<UserAcctTmpRoleMasterStdVO, UserAcctTmpRoleSlaveStdVO, UserAcctTmpRoleUpdatableObj, UserAcctTmpRoleUpdateCtx> {

    @Override
    public UserAcctTmpRoleUpdateCtx analyseUpdatedAttrs(final UserAcctTmpRoleMasterStdVO tmpRole,
                                                        final UserAcctTmpRoleSlaveStdVO existingTmpRole) {
        final UserAcctTmpRoleUpdateCtx updateCtx = new UserAcctTmpRoleUpdateCtx();
        updateCtx.setUpdatableObj(new UserAcctTmpRoleUpdatableObj());
        analyseUpdatedAttrs(tmpRole, existingTmpRole, updateCtx);
        return updateCtx;
    }
}
