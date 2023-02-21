package com.ioiox.dei.duc.db.service.impl.slave.employee;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.UserGrpR2SysResRole;
import com.ioiox.dei.duc.db.mapper.slave.employee.EmployeeUserGrpR2SysResRoleSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.employee.EmployeeUserGrpR2SysResRoleSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeUserGrpR2SysResRoleSlaveDbSvc")
public class EmployeeUserGrpR2SysResRoleSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<UserGrpR2SysResRole, EmployeeUserGrpR2SysResRoleSlaveMapper>
        implements EmployeeUserGrpR2SysResRoleSlaveDbSvc {

    @Autowired
    private EmployeeUserGrpR2SysResRoleSlaveMapper mapper;

    @Override
    protected EmployeeUserGrpR2SysResRoleSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员用户组与系统资源角色关联表";
    }
}
