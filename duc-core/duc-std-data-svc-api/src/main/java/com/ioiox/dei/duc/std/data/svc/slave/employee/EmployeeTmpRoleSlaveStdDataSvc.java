package com.ioiox.dei.duc.std.data.svc.slave.employee;

import com.ioiox.dei.duc.beans.model.slave.RoleQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.employee.EmployeeTmpRoleQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.employee.EmployeeTmpRoleSlaveVO;
import com.ioiox.dei.duc.std.data.svc.slave.RoleSlaveStdDataSvc;

import java.util.List;

public interface EmployeeTmpRoleSlaveStdDataSvc
        extends RoleSlaveStdDataSvc<EmployeeTmpRoleSlaveVO, EmployeeTmpRoleQueryParam> {

    EmployeeTmpRoleSlaveVO queryByPk(final Long pk,
                                     final RoleQueryCfg queryCfg);

    List<EmployeeTmpRoleSlaveVO> queryByPks(final List<Long> pks,
                                            final RoleQueryCfg queryCfg);
}
