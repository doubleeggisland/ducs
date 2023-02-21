package com.ioiox.dei.duc.db.service.impl.slave.employee;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.EmployeeR2Role;
import com.ioiox.dei.duc.db.mapper.slave.employee.EmployeeR2RoleSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.employee.EmployeeR2RoleSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeR2RoleSlaveDbSvc")
public class EmployeeR2RoleSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<EmployeeR2Role, EmployeeR2RoleSlaveMapper>
        implements EmployeeR2RoleSlaveDbSvc {

    @Autowired
    private EmployeeR2RoleSlaveMapper mapper;

    @Override
    protected EmployeeR2RoleSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员与角色关联表";
    }
}
