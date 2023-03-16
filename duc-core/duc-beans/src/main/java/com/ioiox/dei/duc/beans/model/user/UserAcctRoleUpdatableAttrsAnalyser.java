package com.ioiox.dei.duc.beans.model.user;

import com.ioiox.dei.duc.beans.model.RoleUpdatableAttrsAnalyser;
import com.ioiox.dei.duc.beans.vo.std.master.user.UserAcctRoleMasterStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctRoleSlaveStdVO;

public class UserAcctRoleUpdatableAttrsAnalyser
        extends RoleUpdatableAttrsAnalyser<UserAcctRoleMasterStdVO, UserAcctRoleSlaveStdVO, UserAcctRoleUpdatableObj, UserAcctRoleUpdateCtx> {

    @Override
    public UserAcctRoleUpdateCtx analyseUpdatedAttrs(final UserAcctRoleMasterStdVO role,
                                                     final UserAcctRoleSlaveStdVO existingRole) {
        final UserAcctRoleUpdateCtx updateCtx = new UserAcctRoleUpdateCtx();
        updateCtx.setUpdatableObj(new UserAcctRoleUpdatableObj());
        analyseUpdatedAttrs(role, existingRole, updateCtx);
        return updateCtx;
    }
}
