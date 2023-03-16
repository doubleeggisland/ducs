package com.ioiox.dei.duc.beans.model.user;

import com.ioiox.dei.duc.beans.model.UserGrpUpdatableAttrsAnalyser;
import com.ioiox.dei.duc.beans.vo.std.master.user.AcctUserGrpMasterStdVO;
import com.ioiox.dei.duc.beans.vo.std.slave.user.AcctUserGrpSlaveStdVO;

public class AcctUserGrpUpdatableAttrsAnalyser
        extends UserGrpUpdatableAttrsAnalyser<AcctUserGrpMasterStdVO, AcctUserGrpSlaveStdVO, AcctUserGrpUpdatableObj, AcctUserGrpUpdateCtx> {

    @Override
    public AcctUserGrpUpdateCtx analyseUpdatedAttrs(final AcctUserGrpMasterStdVO userGrp,
                                                    final AcctUserGrpSlaveStdVO existingUserGrp) {
        final AcctUserGrpUpdateCtx updateCtx = new AcctUserGrpUpdateCtx();
        updateCtx.setUpdatableObj(new AcctUserGrpUpdatableObj());
        analyseUpdatedAttrs(userGrp, existingUserGrp, updateCtx);
        return updateCtx;
    }
}
