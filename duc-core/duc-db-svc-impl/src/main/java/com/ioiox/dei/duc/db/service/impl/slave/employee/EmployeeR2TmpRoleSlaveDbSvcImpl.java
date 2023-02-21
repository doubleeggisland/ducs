package com.ioiox.dei.duc.db.service.impl.slave.employee;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.EmployeeR2Role;
import com.ioiox.dei.duc.db.mapper.slave.employee.EmployeeR2TmpRoleSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.employee.EmployeeR2TmpRoleSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeR2TmpRoleSlaveDbSvc")
public class EmployeeR2TmpRoleSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<EmployeeR2Role, EmployeeR2TmpRoleSlaveMapper>
        implements EmployeeR2TmpRoleSlaveDbSvc {

    @Autowired
    private EmployeeR2TmpRoleSlaveMapper mapper;

    @Override
    protected EmployeeR2TmpRoleSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员与临时角色关联表";
    }
}
