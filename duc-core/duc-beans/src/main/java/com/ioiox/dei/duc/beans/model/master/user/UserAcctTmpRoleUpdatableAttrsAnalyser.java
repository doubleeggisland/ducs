package com.ioiox.dei.duc.beans.model.master.user;

import com.ioiox.dei.duc.beans.model.master.BaseTmpRoleUpdatableAttrsAnalyser;
import com.ioiox.dei.duc.beans.vo.std.master.user.UserAcctTmpRoleMasterVO;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctTmpRoleSlaveVO;

public class UserAcctTmpRoleUpdatableAttrsAnalyser
        extends BaseTmpRoleUpdatableAttrsAnalyser<UserAcctTmpRoleMasterVO, UserAcctTmpRoleSlaveVO, UserAcctTmpRoleUpdatableObj, UserAcctTmpRoleUpdateCtx> {

    @Override
    public UserAcctTmpRoleUpdateCtx analyseUpdatedAttrs(final UserAcctTmpRoleMasterVO tmpRole,
                                                        final UserAcctTmpRoleSlaveVO existingTmpRole) {
        final UserAcctTmpRoleUpdateCtx updateCtx = new UserAcctTmpRoleUpdateCtx();
        updateCtx.setUpdatableObj(new UserAcctTmpRoleUpdatableObj());
        analyseUpdatedAttrs(tmpRole, existingTmpRole, updateCtx);
        return updateCtx;
    }
}
