package com.ioiox.dei.duc.std.data.svc.slave.employee;

import com.ioiox.dei.duc.beans.model.slave.SysResRoleQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.employee.EmployeeTmpSysResRoleQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.employee.EmployeeTmpSysResRoleSlaveVO;
import com.ioiox.dei.duc.std.data.svc.slave.SysResRoleSlaveStdDataSvc;

import java.util.List;

public interface EmployeeTmpSysResRoleSlaveStdDataSvc
        extends SysResRoleSlaveStdDataSvc<EmployeeTmpSysResRoleSlaveVO, EmployeeTmpSysResRoleQueryParam> {

    EmployeeTmpSysResRoleSlaveVO queryByPk(final Long pk,
                                           final SysResRoleQueryCfg queryCfg);

    List<EmployeeTmpSysResRoleSlaveVO> queryByPks(final List<Long> pks,
                                                  final SysResRoleQueryCfg queryCfg);
}
