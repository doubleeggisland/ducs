package com.ioiox.dei.duc.beans.model.master.employee;

import com.ioiox.dei.duc.beans.model.master.UserGrpUpdatableAttrsAnalyser;
import com.ioiox.dei.duc.beans.vo.std.master.employee.EmployeeUserGrpMasterVO;
import com.ioiox.dei.duc.beans.vo.std.slave.employee.EmployeeUserGrpSlaveVO;

public class EmployeeUserGrpUpdatableAttrsAnalyser
        extends UserGrpUpdatableAttrsAnalyser<EmployeeUserGrpMasterVO, EmployeeUserGrpSlaveVO, EmployeeUserGrpUpdatableObj, EmployeeUserGrpUpdateCtx> {

    @Override
    public EmployeeUserGrpUpdateCtx analyseUpdatedAttrs(final EmployeeUserGrpMasterVO userGrp,
                                                        final EmployeeUserGrpSlaveVO existingUserGrp) {
        final EmployeeUserGrpUpdateCtx updateCtx = new EmployeeUserGrpUpdateCtx();
        updateCtx.setUpdatableObj(new EmployeeUserGrpUpdatableObj());
        analyseUpdatedAttrs(userGrp, existingUserGrp, updateCtx);
        return updateCtx;
    }
}
