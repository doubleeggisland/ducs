package com.ioiox.dei.duc.std.data.svc.slave.employee;

import com.ioiox.dei.duc.beans.model.slave.employee.EmployeeQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.employee.*;
import com.ioiox.dei.duc.std.data.svc.slave.UserSlaveStdDataSvc;

public interface EmployeeSlaveStdDataSvc
        extends UserSlaveStdDataSvc<EmployeeRoleSlaveVO, EmployeeSysResRoleSlaveVO, EmployeeTmpRoleSlaveVO, EmployeeTmpSysResRoleSlaveVO, EmployeeUserGrpSlaveVO, EmployeeSlaveVO, EmployeeQueryParam> {
}
