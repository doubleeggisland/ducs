package com.ioiox.dei.duc.std.data.svc.slave.employee;

import com.ioiox.dei.duc.beans.model.slave.SysResRoleQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.employee.EmployeeSysResRoleQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.employee.EmployeeSysResRoleSlaveVO;
import com.ioiox.dei.duc.std.data.svc.slave.SysResRoleSlaveStdDataSvc;

import java.util.List;

public interface EmployeeSysResRoleSlaveStdDataSvc
        extends SysResRoleSlaveStdDataSvc<EmployeeSysResRoleSlaveVO, EmployeeSysResRoleQueryParam> {

    EmployeeSysResRoleSlaveVO queryByPk(final Long pk,
                                        final SysResRoleQueryCfg queryCfg);

    List<EmployeeSysResRoleSlaveVO> queryByPks(final List<Long> pks,
                                               final SysResRoleQueryCfg queryCfg);
}
