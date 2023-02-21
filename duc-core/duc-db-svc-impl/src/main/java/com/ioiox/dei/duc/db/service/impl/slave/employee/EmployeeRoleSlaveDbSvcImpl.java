package com.ioiox.dei.duc.db.service.impl.slave.employee;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.EmployeeRole;
import com.ioiox.dei.duc.db.mapper.slave.employee.EmployeeRoleSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.employee.EmployeeRoleSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeRoleSlaveDbSvc")
public class EmployeeRoleSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<EmployeeRole, EmployeeRoleSlaveMapper>
        implements EmployeeRoleSlaveDbSvc {

    @Autowired
    private EmployeeRoleSlaveMapper mapper;

    @Override
    protected EmployeeRoleSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员角色表";
    }
}
