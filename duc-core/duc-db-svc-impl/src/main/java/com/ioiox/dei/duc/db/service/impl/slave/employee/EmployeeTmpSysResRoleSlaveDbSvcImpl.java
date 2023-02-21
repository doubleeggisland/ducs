package com.ioiox.dei.duc.db.service.impl.slave.employee;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.EmployeeTmpSysResRole;
import com.ioiox.dei.duc.db.mapper.slave.employee.EmployeeTmpSysResRoleSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.employee.EmployeeTmpSysResRoleSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeTmpSysResRoleSlaveDbSvc")
public class EmployeeTmpSysResRoleSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<EmployeeTmpSysResRole, EmployeeTmpSysResRoleSlaveMapper>
        implements EmployeeTmpSysResRoleSlaveDbSvc {

    @Autowired
    private EmployeeTmpSysResRoleSlaveMapper mapper;

    @Override
    protected EmployeeTmpSysResRoleSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员临时系统资源角色表";
    }
}
