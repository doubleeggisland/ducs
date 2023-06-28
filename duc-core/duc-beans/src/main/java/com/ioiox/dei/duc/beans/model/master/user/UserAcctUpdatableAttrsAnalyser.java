package com.ioiox.dei.duc.beans.model.master.user;

import com.ioiox.dei.duc.beans.model.master.UserUpdatableAttrsAnalyser;
import com.ioiox.dei.duc.beans.vo.std.master.user.UserAcctMasterStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctSlaveStdVO;

public class UserAcctUpdatableAttrsAnalyser
        extends UserUpdatableAttrsAnalyser<UserAcctMasterStdVO, UserAcctSlaveStdVO, UserAcctUpdatableObj, UserAcctUpdateCtx> {

    @Override
    public UserAcctUpdateCtx analyseUpdatedAttrs(final UserAcctMasterStdVO userAcct,
                                                 final UserAcctSlaveStdVO existingUserAcct) {
        final UserAcctUpdateCtx updateCtx = new UserAcctUpdateCtx();
        updateCtx.setUpdatableObj(new UserAcctUpdatableObj());
        analyseUpdatedAttrs(userAcct, existingUserAcct, updateCtx);
        return updateCtx;
    }
}
