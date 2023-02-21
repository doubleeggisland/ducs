package com.ioiox.dei.duc.db.service.impl.slave.employee;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.UserGrpR2Role;
import com.ioiox.dei.duc.db.mapper.slave.employee.EmployeeUserGrpR2RoleSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.employee.EmployeeUserGrpR2RoleSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeUserGrpR2RoleSlaveDbSvc")
public class EmployeeUserGrpR2RoleSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<UserGrpR2Role, EmployeeUserGrpR2RoleSlaveMapper>
        implements EmployeeUserGrpR2RoleSlaveDbSvc {

    @Autowired
    private EmployeeUserGrpR2RoleSlaveMapper mapper;

    @Override
    protected EmployeeUserGrpR2RoleSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员用户组与角色关联表";
    }
}
