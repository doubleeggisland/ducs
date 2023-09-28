package com.ioiox.dei.duc.std.data.svc.slave.employee;

import com.ioiox.dei.duc.beans.model.slave.UserQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.employee.EmployeeQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.employee.*;
import com.ioiox.dei.duc.std.data.svc.slave.UserSlaveStdDataSvc;

import java.util.List;

public interface EmployeeSlaveStdDataSvc
        extends UserSlaveStdDataSvc<EmployeeRoleSlaveVO, EmployeeSysResRoleSlaveVO, EmployeeTmpRoleSlaveVO, EmployeeTmpSysResRoleSlaveVO, EmployeeUserGrpSlaveVO, EmployeeSlaveVO, EmployeeQueryParam> {

    EmployeeSlaveVO queryByPk(final Long pk,
                              final UserQueryCfg queryCfg);

    List<EmployeeSlaveVO> queryByPks(final List<Long> pks,
                                     final UserQueryCfg queryCfg);
}
