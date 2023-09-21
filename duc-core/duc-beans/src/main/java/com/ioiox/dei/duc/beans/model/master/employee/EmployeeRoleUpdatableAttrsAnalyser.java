package com.ioiox.dei.duc.beans.model.master.employee;

import com.ioiox.dei.duc.beans.model.master.SimpleRoleUpdatableAttrsAnalyser;
import com.ioiox.dei.duc.beans.vo.std.master.employee.EmployeeRoleMasterVO;
import com.ioiox.dei.duc.beans.vo.std.slave.employee.EmployeeRoleSlaveVO;

public class EmployeeRoleUpdatableAttrsAnalyser
        extends SimpleRoleUpdatableAttrsAnalyser<EmployeeRoleMasterVO, EmployeeRoleSlaveVO, EmployeeRoleUpdatableObj, EmployeeRoleUpdateCtx> {

    @Override
    public EmployeeRoleUpdateCtx analyseUpdatedAttrs(final EmployeeRoleMasterVO role,
                                                     final EmployeeRoleSlaveVO existingRole) {
        final EmployeeRoleUpdateCtx updateCtx = new EmployeeRoleUpdateCtx();
        updateCtx.setUpdatableObj(new EmployeeRoleUpdatableObj());
        analyseUpdatedAttrs(role, existingRole, updateCtx);
        return updateCtx;
    }
}
