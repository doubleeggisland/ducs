package com.ioiox.dei.duc.db.service.impl.slave.employee;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.EmployeeSysResRole;
import com.ioiox.dei.duc.db.mapper.slave.employee.EmployeeSysResRoleSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.employee.EmployeeSysResRoleSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeSysResRoleSlaveDbSvc")
public class EmployeeSysResRoleSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<EmployeeSysResRole, EmployeeSysResRoleSlaveMapper>
        implements EmployeeSysResRoleSlaveDbSvc {

    @Autowired
    private EmployeeSysResRoleSlaveMapper mapper;

    @Override
    protected EmployeeSysResRoleSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员系统资源角色表";
    }
}
