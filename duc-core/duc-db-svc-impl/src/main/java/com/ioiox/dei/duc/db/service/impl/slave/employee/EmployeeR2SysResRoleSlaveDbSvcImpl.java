package com.ioiox.dei.duc.db.service.impl.slave.employee;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.EmployeeR2SysResRole;
import com.ioiox.dei.duc.db.mapper.slave.employee.EmployeeR2SysResRoleSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.employee.EmployeeR2SysResRoleSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeR2SysResRoleSlaveDbSvc")
public class EmployeeR2SysResRoleSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<EmployeeR2SysResRole, EmployeeR2SysResRoleSlaveMapper>
        implements EmployeeR2SysResRoleSlaveDbSvc {

    @Autowired
    private EmployeeR2SysResRoleSlaveMapper mapper;

    @Override
    protected EmployeeR2SysResRoleSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员与系统资源角色关联表";
    }
}
