package com.ioiox.dei.duc.db.service.impl.slave.employee;

import com.ioiox.dei.core.orm.mybatis.service.BaseDeiSlaveDbService;
import com.ioiox.dei.duc.beans.entity.RoleR2SysApi;
import com.ioiox.dei.duc.db.mapper.slave.employee.EmployeeRoleR2SysApiSlaveMapper;
import com.ioiox.dei.duc.db.service.slave.employee.EmployeeRoleR2SysApiSlaveDbSvc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("employeeRoleR2SysApiSlaveDbSvc")
public class EmployeeRoleR2SysApiSlaveDbSvcImpl
        extends BaseDeiSlaveDbService<RoleR2SysApi, EmployeeRoleR2SysApiSlaveMapper>
        implements EmployeeRoleR2SysApiSlaveDbSvc {

    @Autowired
    private EmployeeRoleR2SysApiSlaveMapper mapper;

    @Override
    protected EmployeeRoleR2SysApiSlaveMapper getMapper() {
        return mapper;
    }

    @Override
    protected String getDesc() {
        return "雇员角色与系统API关联表";
    }
}
