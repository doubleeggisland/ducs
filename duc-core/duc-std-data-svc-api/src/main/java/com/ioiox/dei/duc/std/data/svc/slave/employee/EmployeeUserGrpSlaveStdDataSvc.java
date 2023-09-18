package com.ioiox.dei.duc.std.data.svc.slave.employee;

import com.ioiox.dei.duc.beans.model.slave.UserGrpQueryCfg;
import com.ioiox.dei.duc.beans.model.slave.employee.EmployeeUserGrpQueryParam;
import com.ioiox.dei.duc.beans.vo.std.slave.employee.EmployeeRoleSlaveVO;
import com.ioiox.dei.duc.beans.vo.std.slave.employee.EmployeeSysResRoleSlaveVO;
import com.ioiox.dei.duc.beans.vo.std.slave.employee.EmployeeUserGrpSlaveVO;
import com.ioiox.dei.duc.std.data.svc.slave.UserGrpSlaveStdDataSvc;

import java.util.List;

public interface EmployeeUserGrpSlaveStdDataSvc
        extends UserGrpSlaveStdDataSvc<EmployeeRoleSlaveVO, EmployeeSysResRoleSlaveVO, EmployeeUserGrpSlaveVO, EmployeeUserGrpQueryParam> {

    EmployeeUserGrpSlaveVO queryByPk(final Long pk,
                                     final UserGrpQueryCfg queryCfg);

    List<EmployeeUserGrpSlaveVO> queryByPks(final List<Long> pks,
                                            final UserGrpQueryCfg queryCfg);
}
