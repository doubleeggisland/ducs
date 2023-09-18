package com.ioiox.dei.duc.beans.model.master.user;

import com.ioiox.dei.duc.beans.model.master.UserUpdatableAttrsAnalyser;
import com.ioiox.dei.duc.beans.vo.std.master.user.UserAcctMasterVO;
import com.ioiox.dei.duc.beans.vo.std.slave.user.UserAcctSlaveVO;

public class UserAcctUpdatableAttrsAnalyser
        extends UserUpdatableAttrsAnalyser<UserAcctMasterVO, UserAcctSlaveVO, UserAcctUpdatableObj, UserAcctUpdateCtx> {

    @Override
    public UserAcctUpdateCtx analyseUpdatedAttrs(final UserAcctMasterVO userAcct,
                                                 final UserAcctSlaveVO existingUserAcct) {
        final UserAcctUpdateCtx updateCtx = new UserAcctUpdateCtx();
        updateCtx.setUpdatableObj(new UserAcctUpdatableObj());
        analyseUpdatedAttrs(userAcct, existingUserAcct, updateCtx);
        return updateCtx;
    }
}
