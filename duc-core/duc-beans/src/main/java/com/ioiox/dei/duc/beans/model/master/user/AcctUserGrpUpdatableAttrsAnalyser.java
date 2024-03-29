package com.ioiox.dei.duc.beans.model.master.user;

import com.ioiox.dei.duc.beans.model.master.UserGrpUpdatableAttrsAnalyser;
import com.ioiox.dei.duc.beans.vo.std.master.user.AcctUserGrpMasterVO;
import com.ioiox.dei.duc.beans.vo.std.slave.user.AcctUserGrpSlaveVO;

public class AcctUserGrpUpdatableAttrsAnalyser
        extends UserGrpUpdatableAttrsAnalyser<AcctUserGrpMasterVO, AcctUserGrpSlaveVO, AcctUserGrpUpdatableObj, AcctUserGrpUpdateCtx> {

    @Override
    public AcctUserGrpUpdateCtx analyseUpdatedAttrs(final AcctUserGrpMasterVO userGrp,
                                                    final AcctUserGrpSlaveVO existingUserGrp) {
        final AcctUserGrpUpdateCtx updateCtx = new AcctUserGrpUpdateCtx();
        updateCtx.setUpdatableObj(new AcctUserGrpUpdatableObj());
        analyseUpdatedAttrs(userGrp, existingUserGrp, updateCtx);
        return updateCtx;
    }
}
