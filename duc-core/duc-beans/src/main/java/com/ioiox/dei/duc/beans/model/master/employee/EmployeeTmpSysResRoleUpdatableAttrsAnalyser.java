package com.ioiox.dei.duc.beans.model.master.employee;

import com.ioiox.dei.duc.beans.model.master.BaseTmpRoleUpdatableAttrsAnalyser;
import com.ioiox.dei.duc.beans.vo.std.master.employee.EmployeeTmpSysResRoleMasterVO;
import com.ioiox.dei.duc.beans.vo.std.slave.employee.EmployeeTmpSysResRoleSlaveVO;

public class EmployeeTmpSysResRoleUpdatableAttrsAnalyser
        extends BaseTmpRoleUpdatableAttrsAnalyser<EmployeeTmpSysResRoleMasterVO, EmployeeTmpSysResRoleSlaveVO, EmployeeTmpSysResRoleUpdatableObj, EmployeeTmpSysResRoleUpdateCtx> {

    @Override
    public EmployeeTmpSysResRoleUpdateCtx analyseUpdatedAttrs(final EmployeeTmpSysResRoleMasterVO tmpSysResRole,
                                                              final EmployeeTmpSysResRoleSlaveVO existingTmpSysResRole) {
        final EmployeeTmpSysResRoleUpdateCtx updateCtx = new EmployeeTmpSysResRoleUpdateCtx();
        updateCtx.setUpdatableObj(new EmployeeTmpSysResRoleUpdatableObj());
        analyseUpdatedAttrs(tmpSysResRole, existingTmpSysResRole, updateCtx);
        return updateCtx;
    }
}
