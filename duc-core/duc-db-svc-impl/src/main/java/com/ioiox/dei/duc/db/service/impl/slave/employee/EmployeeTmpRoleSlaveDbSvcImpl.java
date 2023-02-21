package com.ioiox.dei.duc.db.service.impl.slave.employee;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.EmployeeTmpRole;
import com.ioiox.dei.duc.db.mapper.slave.employee.EmployeeTmpRoleSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.employee.EmployeeTmpRoleSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeTmpRoleSlaveDbSvc")
public class EmployeeTmpRoleSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<EmployeeTmpRole, EmployeeTmpRoleSlaveMapper>
        implements EmployeeTmpRoleSlaveDbSvc {

    @Autowired
    private EmployeeTmpRoleSlaveMapper mapper;

    @Override
    protected EmployeeTmpRoleSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员临时角色表";
    }
}
