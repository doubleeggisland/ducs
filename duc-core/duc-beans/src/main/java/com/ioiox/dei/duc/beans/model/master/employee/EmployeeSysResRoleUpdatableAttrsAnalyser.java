package com.ioiox.dei.duc.beans.model.master.employee;

import com.ioiox.dei.duc.beans.model.master.BaseRoleUpdatableAttrsAnalyser;
import com.ioiox.dei.duc.beans.vo.std.master.employee.EmployeeSysResRoleMasterVO;
import com.ioiox.dei.duc.beans.vo.std.slave.employee.EmployeeSysResRoleSlaveVO;

public class EmployeeSysResRoleUpdatableAttrsAnalyser
        extends BaseRoleUpdatableAttrsAnalyser<EmployeeSysResRoleMasterVO, EmployeeSysResRoleSlaveVO, EmployeeSysResRoleUpdatableObj, EmployeeSysResRoleUpdateCtx> {

    @Override
    public EmployeeSysResRoleUpdateCtx analyseUpdatedAttrs(final EmployeeSysResRoleMasterVO sysResRole,
                                                           final EmployeeSysResRoleSlaveVO existingSysResRole) {
        final EmployeeSysResRoleUpdateCtx updateCtx = new EmployeeSysResRoleUpdateCtx();
        updateCtx.setUpdatableObj(new EmployeeSysResRoleUpdatableObj());
        analyseUpdatedAttrs(sysResRole, existingSysResRole, updateCtx);
        return updateCtx;
    }
}
