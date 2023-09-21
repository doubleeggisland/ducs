package com.ioiox.dei.duc.beans.model.master.employee;

import com.ioiox.dei.duc.beans.model.master.SimpleRoleUpdatableAttrsAnalyser;
import com.ioiox.dei.duc.beans.vo.std.master.employee.EmployeeTmpRoleMasterVO;
import com.ioiox.dei.duc.beans.vo.std.slave.employee.EmployeeTmpRoleSlaveVO;

public class EmployeeTmpRoleUpdatableAttrsAnalyser
        extends SimpleRoleUpdatableAttrsAnalyser<EmployeeTmpRoleMasterVO, EmployeeTmpRoleSlaveVO, EmployeeTmpRoleUpdatableObj, EmployeeTmpRoleUpdateCtx> {

    @Override
    public EmployeeTmpRoleUpdateCtx analyseUpdatedAttrs(final EmployeeTmpRoleMasterVO tmpRole,
                                                        final EmployeeTmpRoleSlaveVO existingTmpRole) {
        final EmployeeTmpRoleUpdateCtx updateCtx = new EmployeeTmpRoleUpdateCtx();
        updateCtx.setUpdatableObj(new EmployeeTmpRoleUpdatableObj());
        analyseUpdatedAttrs(tmpRole, existingTmpRole, updateCtx);
        return updateCtx;
    }
}
