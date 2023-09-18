package com.ioiox.dei.duc.std.data.svc.slave.employee;

import com.ioiox.dei.duc.beans.model.slave.RoleQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.employee.EmployeeRoleQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.employee.EmployeeRoleSlaveVO;
import com.ioiox.dei.duc.std.data.svc.slave.RoleSlaveStdDataSvc;

import java.util.List;

public interface EmployeeRoleSlaveStdDataSvc
        extends RoleSlaveStdDataSvc<EmployeeRoleSlaveVO, EmployeeRoleQueryParam> {

    EmployeeRoleSlaveVO queryByPk(final Long pk, final RoleQueryCfg queryCfg);

    List<EmployeeRoleSlaveVO> queryByPKs(final List<Long> pks, final RoleQueryCfg queryCfg);
}
