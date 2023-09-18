package com.ioiox.dei.duc.beans.model.master.user;

import com.ioiox.dei.duc.beans.model.master.BaseRoleUpdatableAttrsAnalyser;
import com.ioiox.dei.duc.beans.vo.std.master.user.UserAcctRoleMasterVO;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctRoleSlaveVO;

public class UserAcctRoleUpdatableAttrsAnalyser
        extends BaseRoleUpdatableAttrsAnalyser<UserAcctRoleMasterVO, UserAcctRoleSlaveVO, UserAcctRoleUpdatableObj, UserAcctRoleUpdateCtx> {

    @Override
    public UserAcctRoleUpdateCtx analyseUpdatedAttrs(final UserAcctRoleMasterVO role,
                                                     final UserAcctRoleSlaveVO existingRole) {
        final UserAcctRoleUpdateCtx updateCtx = new UserAcctRoleUpdateCtx();
        updateCtx.setUpdatableObj(new UserAcctRoleUpdatableObj());
        analyseUpdatedAttrs(role, existingRole, updateCtx);
        return updateCtx;
    }
}
